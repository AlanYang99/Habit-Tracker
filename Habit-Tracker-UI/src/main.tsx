import { Provider } from "./components/ui/provider.tsx";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import {
  Route,
  RouterProvider,
  createRoutesFromElements,
  createBrowserRouter,
} from "react-router";
import "./index.css";
import App from "./App.tsx";
import { AuthProvider } from "./store/AuthContext.tsx";
import DashboardPage from "./pages/DashboardPage.tsx";
import DueHabitsPage from "./pages/DueHabitsPage.tsx";
import HomePage from "./pages/HomePage.tsx";
import LoginPage from "./pages/LoginPage.tsx";
import RegisterPage from "./pages/RegisterPage.tsx";
import HabitPage from "./pages/HabitPage.tsx";
import { dashboardLoader } from "./pages/DashboardPage.tsx";
import { dueHabitsLoader } from "./pages/DueHabitsPage.tsx";
import { habitsLoader } from "./pages/HabitPage.tsx";
import { loginAction } from "./pages/LoginPage.tsx";
import { registerAction } from "./pages/RegisterPage.tsx";
import { addHabitAction } from "./pages/HabitPage.tsx";
import { Toaster } from "@/components/ui/toaster";
import SecuredPage from "./pages/SecuredPage.tsx";

const routeDefinitions = createRoutesFromElements(
  <Route path="/" element={<App />}>
    <Route index element={<HomePage />} />
    <Route path="/login" element={<LoginPage />} action={loginAction} />
    <Route
      path="/register"
      element={<RegisterPage />}
      action={registerAction}
    />
    <Route element={<SecuredPage />}>
      <Route
        path="/dashboard"
        element={<DashboardPage />}
        loader={dashboardLoader}
      />
      <Route
        path="/dueHabits"
        element={<DueHabitsPage />}
        loader={dueHabitsLoader}
      />
      <Route
        path="/habits"
        element={<HabitPage />}
        loader={habitsLoader}
        action={addHabitAction}
      />
    </Route>
  </Route>
);

const appRouter = createBrowserRouter(routeDefinitions);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <AuthProvider>
      <Provider themes={["light", "dark"]} defaultTheme="light">
        <RouterProvider router={appRouter} />
        <Toaster />
      </Provider>
    </AuthProvider>
  </StrictMode>
);
