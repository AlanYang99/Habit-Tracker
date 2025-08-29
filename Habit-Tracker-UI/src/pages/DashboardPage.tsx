import { Box, Flex, Grid, GridItem } from "@chakra-ui/react";
import { useLoaderData } from "react-router";
import apiClient from "@/api/apiClient";
import type { AxiosError } from "axios";
import SideBar from "@/Sidebar";
import Header from "@/Header";
import Welcome from "@/Welcome";

export default function DashboardPage() {
  const { userData, dueHabits } = useLoaderData();

  return (
    <>
      <Flex direction="column" h="100vh">
        <Header />
        <Flex flex="1" overflow="hidden">
          <SideBar />

          <Grid
            bgGradient="to-br"
            gradientFrom="purple.600"
            gradientVia="teal.400"
            gradientTo="blue.200"
            w="100%"
            gap={4}
            templateColumns={{
              base: "repeat(2, 1fr)", // 👈 2 columns on mobile
              md: "repeat(4, 1fr)", // 👈 4 columns on medium screens and up
            }}
            templateAreas={{
              base: `
                    "welcome welcome"
                    "summary summary"
                    "stats stats"
                    "habits habits"
                    "timeline timeline"
                    "bar circle"
                  `,
              md: `
                    "welcome welcome summary summary"
                    "welcome welcome stats stats"
                    "habits habits timeline timeline"
                    "habits habits bar circle"
                  `,
            }}
          >
            <GridItem area="welcome">
              <Welcome user={userData} />
            </GridItem>
          </Grid>
        </Flex>
      </Flex>
    </>
  );
}

export async function dashboardLoader() {
  try {
    const userResponse = await apiClient.get("/auth/user-summary");
    const dueHabits = await apiClient.get("/habit");
    return { userData: userResponse?.data, dueHabitsData: dueHabits?.data };
  } catch (error) {
    const axiosError = error as AxiosError;
    throw new Response("Failed to fetch information for user", {
      status: axiosError.status || 500,
    });
  }
}
