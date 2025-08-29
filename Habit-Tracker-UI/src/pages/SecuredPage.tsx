import { Outlet, Navigate } from "react-router";
import { useAuth } from "../store/AuthContext";

export default function SecuredPage() {
  const { isAuthenticated } = useAuth();

  return isAuthenticated ? <Outlet /> : <Navigate to="/" />;
}
