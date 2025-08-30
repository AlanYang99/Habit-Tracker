import Habit from "./Habit";
import { Box, Heading, Text, Center } from "@chakra-ui/react";

interface HabitProps {
  name: string;
  description: string;
  completionStatus: boolean;
}

interface HabitPropsListProps {
  habits: HabitProps[];
}

export default function HabitList({ habits }: HabitPropsListProps) {
  return (
    <Box
      ml="20px"
      mr="20px"
      textAlign="center"
      flexDirection="column"
      display="flex"
      border="2px"
      background="white/60"
      rounded="4xl"
      h="600px"
      width="1000px"
    >
      <Heading as="h1" size="xl" mb={4} mt={4}>
        Todays Tasks
      </Heading>

      {Array.isArray(habits) && habits.length > 0 ? (
        habits.map((habit: HabitProps, index: number) => (
          <Habit key={index} habit={habit} />
        ))
      ) : (
        <Center>
          <Text>No habits due today</Text>
        </Center>
      )}
    </Box>
  );
}
