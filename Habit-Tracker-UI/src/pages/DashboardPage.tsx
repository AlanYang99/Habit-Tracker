import { Grid, GridItem, Box } from "@chakra-ui/react";
import { useLoaderData } from "react-router";
import apiClient from "@/api/apiClient";
import type { AxiosError } from "axios";
import Welcome from "@/components/Welcome";
import UserStatList from "@/components/UserStatList";
import { ImFire } from "react-icons/im";
import { SlBadge } from "react-icons/sl";
import { FaHeartCircleCheck } from "react-icons/fa6";
import HabitList from "@/components/HabitList";
import WeeklyStatus from "@/components/WeeklyStatus";
import DailyProgressCircle from "@/components/DailyProgressCircle";
import UserActions from "@/components/UserActions";

interface UserDataProps {
  username: string;
  email: string;
  name: string;
  gameSummary: {
    level: number;
    experience: number;
  };
  streakSummary: {
    currentStreak: number;
    longestStreak: number;
  };
  badges: number;
}

interface HabitProps {
  habitId: number;
  name: string;
  description: string;
  completionStatus: boolean;
}

interface HabitLogs {
  creationDate: Date;
  notes: string;
}

export default function DashboardPage() {
  const { userData, dueHabitsData, userActions } = useLoaderData<{
    userData: UserDataProps;
    dueHabitsData: HabitProps[];
    userActions: HabitLogs[];
  }>();

  return (
    <Box flex="1" overflowY="auto">
      <Grid
        bgGradient="to-br"
        gradientFrom="purple.600"
        gradientVia="teal.400"
        gradientTo="blue.200"
        w="100%"
        minH="100%"
        flex="1"
        gap={4}
        templateColumns={{
          base: "repeat(2, 1fr)", // 👈 2 columns on mobile
          xl: "repeat(4, 1fr)", // 👈 4 columns on medium screens and up
        }}
        templateAreas={{
          base: `
                    "welcome welcome"
                    "summary summary"
                    "habits habits"
                    "bar circle"
                    "timeline timeline"
                  `,
          xl: `
                    "welcome welcome summary summary"
                    "bar    bar    circle circle"
                    "habits habits habits timeline"
                    "habits habits habits timeline"
                  `,
          "2xl": `
                    "welcome welcome summary summary"
                    "habits habits bar timeline"
                    "habits habits circle timeline"
                  `,
        }}
      >
        <GridItem area="welcome" display="flex" justifyContent="center">
          <Welcome user={userData} />
        </GridItem>
        <GridItem area="summary" display="flex" justifyContent="center">
          <UserStatList
            user={userData}
            statList={[
              {
                title: "Current Streak",
                value: userData?.streakSummary?.currentStreak,
                icon: <ImFire />,
              },
              {
                title: "Habits Completed",
                value: "10",
                icon: <FaHeartCircleCheck />,
              },
              { title: "Badges Earned", value: "0", icon: <SlBadge /> },
            ]}
          />
        </GridItem>
        <GridItem
          area="habits"
          display="flex"
          justifyContent="center"
          overflow="auto"
        >
          <HabitList habits={dueHabitsData} width="1000px" height="600px" />
        </GridItem>

        <GridItem area="bar" display="flex" justifyContent="center">
          <WeeklyStatus />
        </GridItem>

        <GridItem area="circle" display="flex" justifyContent="center">
          <DailyProgressCircle />
        </GridItem>

        <GridItem area="timeline" display="flex">
          <UserActions habitlogs={userActions} />
        </GridItem>
      </Grid>
    </Box>
  );
}

export async function dashboardLoader() {
  try {
    const userResponse = await apiClient.get("/auth/user-summary");
    const dueHabits = await apiClient.get("/habit/due");
    const actions = await apiClient.get("/habit-log");
    return {
      userData: userResponse?.data,
      dueHabitsData: dueHabits?.data,
      userActions: actions?.data,
    };
  } catch (error) {
    const axiosError = error as AxiosError;
    throw new Response("Failed to fetch information for user", {
      status: axiosError.status || 500,
    });
  }
}
