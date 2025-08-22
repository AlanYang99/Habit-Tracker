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
import HomePage from "./components/pages/HomePage.tsx";
import LoginPage from "./components/pages/LoginPage.tsx";
import RegisterPage from "./components/pages/RegisterPage.tsx";

const routeDefinitions = createRoutesFromElements(
  <Route path="/" element={<App />}>
    <Route index element={<HomePage />} />
    <Route path="/login" element={<LoginPage />} />
    <Route path="/register" element={<RegisterPage />} />
  </Route>
);

const appRouter = createBrowserRouter(routeDefinitions);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Provider themes={["light", "dark"]} defaultTheme="light">
      <RouterProvider router={appRouter} />
    </Provider>
  </StrictMode>
);
