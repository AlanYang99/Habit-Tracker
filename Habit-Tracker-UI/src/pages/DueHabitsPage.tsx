import { Flex, Grid, GridItem, Box } from "@chakra-ui/react";
import { useLoaderData } from "react-router";
import apiClient from "@/api/apiClient";
import type { AxiosError } from "axios";
import HabitList from "@/components/HabitList";

interface HabitProps {
  habitId: number;
  name: string;
  description: string;
  completionStatus: boolean;
}

export default function DueHabitsPage() {
  const { dueHabitsData } = useLoaderData<{
    dueHabitsData: HabitProps[];
  }>();
  return (
    <Box
      h="95vh"
      w="100%"
      bgGradient="to-br"
      gradientFrom="purple.600"
      gradientVia="teal.400"
      gradientTo="blue.200"
    >
      <HabitList habits={dueHabitsData} width="70%" height="85%"></HabitList>
    </Box>
  );
}

export async function dueHabitsLoader() {
  try {
    const dueHabits = await apiClient.get("/habit/due");
    return { dueHabitsData: dueHabits?.data };
  } catch (error) {
    const axiosError = error as AxiosError;
    throw new Response("Failed to fetch information for user", {
      status: axiosError.status || 500,
    });
  }
}
