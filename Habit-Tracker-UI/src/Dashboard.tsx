import { Flex, Box, VStack, HStack, Grid, GridItem } from "@chakra-ui/react";
import Header from "./Header";
import SideBar from "./Sidebar";
import Welcome from "./components/Welcome";
import GameSummary from "./GameSummary";
import Habits from "./Habits";
import GameStatsSummary from "./GameStatsSummary";
import DateCard from "./DateCard";
import MotivationalSpeech from "./MotivationalSpeech";
import CompletionGraph from "./CompletionGraph";
import LineGraph from "./LineGraph";
import HabitList from "./HabitList";
import CustomTimeline from "./Timeline";
import BarGraph from "./BarGraph";
import CompletionCircle from "./CompletionCircle";

export default function Dashboard() {
  return (
    <Flex direction="column" h="100vh">
      <Header />

      <Flex flex="1" overflow="hidden">
        <SideBar />

        <Box as="main" flex="1" overflowY="auto" bgColor="gray.200">
          {/* <VStack h="100%" w="100%" p={4} align="stretch"> */}
          {/* Top Grid */}
          <Grid
            w="100%"
            templateRows="repeat(4, 1fr)"
            templateColumns="repeat(4, 1fr)"
            gap={4}
          >
            <GridItem rowSpan={2} colSpan={2}>
              <Welcome />
            </GridItem>

            <GridItem rowSpan={1} colSpan={2}>
              <GameSummary />
            </GridItem>

            <GridItem rowSpan={1} colSpan={2}>
              <GameStatsSummary />
            </GridItem>

            <GridItem rowSpan={2} colSpan={2}>
              <HabitList />
            </GridItem>

            <GridItem rowSpan={1} colSpan={2}>
              <CustomTimeline />
            </GridItem>

            <GridItem rowSpan={1} colSpan={1}>
              <BarGraph />
            </GridItem>

            <GridItem rowSpan={1} colSpan={1}>
              <CompletionCircle />
            </GridItem>
          </Grid>

          {/* <GridItem colSpan={3} rowSpan={1}>
                <BarGraph />
              </GridItem> */}
          {/* </VStack> */}
          {/* <HStack align="start">
            <VStack align="center">
              <Welcome />
              <MotivationalSpeech />
              <DateCard />

              <Habits />
            </VStack>
            <VStack align="center">
              <GameStatsSummary />
              <CompletionGraph />
              <LineGraph />
            </VStack>
          </HStack> */}
        </Box>
      </Flex>
    </Flex>
  );
}
