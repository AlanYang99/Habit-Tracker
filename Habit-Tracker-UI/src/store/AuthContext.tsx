import { createContext, useContext, useEffect, useReducer } from "react";
import type { ReactNode } from "react";

interface User {
  id: string;
  name: string;
  email: string;
}

interface AuthState {
  jwtToken: string | null;
  user: User | null;
  isAuthenticated: boolean;
}

interface LoginPayload {
  jwtToken: string;
  user: User;
}

type AuthAction =
  | { type: "LOGIN_SUCCESS"; payload: LoginPayload }
  | { type: "LOGOUT" };

interface AuthContextType extends AuthState {
  loginSuccess: (jwtToken: string, user: User) => void;
  logout: () => void;
}

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthContext = createContext<AuthContextType | undefined>(
  undefined
);

export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

// Reducer
const authReducer = (state: AuthState, action: AuthAction): AuthState => {
  switch (action.type) {
    case "LOGIN_SUCCESS":
      return {
        ...state,
        jwtToken: action.payload.jwtToken,
        user: action.payload.user,
        isAuthenticated: true,
      };
    case "LOGOUT":
      return {
        ...state,
        jwtToken: null,
        user: null,
        isAuthenticated: false,
      };
    default:
      return state;
  }
};

export const AuthProvider = ({ children }: AuthProviderProps) => {
  const initialAuthState: AuthState = (() => {
    try {
      const jwtToken = localStorage.getItem("jwtToken");
      const user = localStorage.getItem("user");
      if (jwtToken && user) {
        return {
          jwtToken,
          user: JSON.parse(user),
          isAuthenticated: true,
        };
      }
    } catch (error) {
      console.error("Failed to load from localStorage:", error);
    }
    return {
      jwtToken: null,
      user: null,
      isAuthenticated: false,
    };
  })();

  const [authState, dispatch] = useReducer(authReducer, initialAuthState);

  useEffect(() => {
    try {
      if (authState.isAuthenticated) {
        localStorage.setItem("jwtToken", authState.jwtToken!);
        localStorage.setItem("user", JSON.stringify(authState.user));
      } else {
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("user");
      }
    } catch (error) {
      console.error("Failed to save to localStorage:", error);
    }
  }, [authState]);

  const loginSuccess = (jwtToken: string, user: User) => {
    dispatch({ type: "LOGIN_SUCCESS", payload: { jwtToken, user } });
  };

  const logout = () => {
    dispatch({ type: "LOGOUT" });
  };

  return (
    <AuthContext.Provider
      value={{
        jwtToken: authState.jwtToken,
        user: authState.user,
        isAuthenticated: authState.isAuthenticated,
        loginSuccess,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
