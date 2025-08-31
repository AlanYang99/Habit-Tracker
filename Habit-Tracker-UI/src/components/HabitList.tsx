import Habit from "./Habit";
import { Box, Heading, Text, Center, VStack } from "@chakra-ui/react";

interface HabitProps {
  habitId: number;
  name: string;
  description: string;
  completionStatus: boolean;
}

export default function HabitList({
  habits,
  width,
  height,
}: {
  habits: HabitProps[];
  width?: string;
  height?: string;
}) {
  return (
    <Box
      ml="20px"
      mr="20px"
      mt="20px"
      textAlign="center"
      flexDirection="column"
      display="flex"
      border="2px"
      background="white/60"
      rounded="4xl"
      h={height}
      width={width}
    >
      <Heading as="h1" size="xl" mb={4} mt={4}>
        Todays Tasks
      </Heading>

      {Array.isArray(habits) && habits.length > 0 ? (
        <VStack mx={6}>
          {habits.map((habit) => (
            <Habit key={habit.habitId} habit={habit} />
          ))}
        </VStack>
      ) : (
        <>
          <Center>
            <Text>No habits due today</Text>
          </Center>
        </>
      )}
    </Box>
  );
}
