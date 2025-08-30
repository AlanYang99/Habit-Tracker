import Habit from "./Habit";
import { Box, Heading, Text, Center, VStack } from "@chakra-ui/react";

interface HabitProps {
  name: string;
  description: string;
  completionStatus: boolean;
}

interface HabitPropsListProps {
  habits: HabitProps[];
}

export default function HabitList({ habits }: HabitPropsListProps) {
  const mockhabit = {
    name: "hello",
    description: "hello",
    completionStatus: false,
  };
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
        <VStack mx={6}>
          {habits.map((habit, index) => (
            <Habit key={index} habit={habit} />
          ))}
        </VStack>
      ) : (
        <>
          <Center>
            <Text>No habits due today</Text>
          </Center>
          <VStack mx={6}>
            {" "}
            <Habit key={3} habit={mockhabit} />
          </VStack>
        </>
      )}
    </Box>
  );
}
