import { Outlet, Navigate } from "react-router";
import { useAuth } from "../store/AuthContext";
import { Flex } from "@chakra-ui/react";
import Header from "@/components/Header";
import SideBar from "@/components/Sidebar";

export default function SecuredPage() {
  const { isAuthenticated } = useAuth();

  return isAuthenticated ? (
    <>
      <Flex direction="column" minH="100vh" minW="100vw">
        <Header />
        <Flex flex="1" overflow="hidden">
          <SideBar />
          <Outlet />
        </Flex>
      </Flex>
    </>
  ) : (
    <Navigate to="/" />
  );
}
