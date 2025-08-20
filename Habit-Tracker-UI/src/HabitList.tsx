import CustomCard from "./CustomCard";
import {
  Box,
  Heading,
  HStack,
  VStack,
  CheckboxCard,
  Circle,
} from "@chakra-ui/react";
import { IoMdFitness } from "react-icons/io";

import type { Key } from "react";

export default function HabitList() {
  return (
    <Box w="100%" h="100%" pl={4} pb={4}>
      <CustomCard align="start">
        <Box w="100%">
          <Heading as="h2" size="lg" mb={4} textAlign="start">
            Todays Tasks 24/10/2023
          </Heading>
          <VStack gap={4} align="stretch" width="100%">
            <Habit key={"1"} />
            <Habit key={"2"} />
            <Habit key={"3"} />
            <Habit key={"4"} />
            <Habit key={"5"} />
            <Habit key={"6"} />
          </VStack>
        </Box>
      </CustomCard>
    </Box>
  );
}

type HabitProps = {
  key?: string;
};

const Habit = ({ key }: HabitProps) => {
  return (
    <CheckboxCard.Root
      defaultChecked={false}
      key={key}
      variant="surface"
      colorPalette="green"
      boxShadow="md"
      size="sm"
      rounded="3xl"
      width="100%"
    >
      <CheckboxCard.HiddenInput />
      <CheckboxCard.Control
        display="grid"
        gridTemplateColumns="10% 80% 10%"
        alignItems="center"
        gap={2}
        p={2}
      >
        {/* 10% column: icon */}
        <CheckboxCard.Content justifyContent="center">
          <Circle
            size="40px"
            bg="teal.50"
            color="teal.500"
            border="2px solid"
            borderColor="teal.300"
          >
            <IoMdFitness />
          </Circle>
        </CheckboxCard.Content>

        {/* 80% column: text */}
        <CheckboxCard.Content textAlign="left">
          <CheckboxCard.Label>Running</CheckboxCard.Label>
          <CheckboxCard.Description>Run 5km</CheckboxCard.Description>
        </CheckboxCard.Content>

        {/* 10% column: checkbox indicator */}
        <CheckboxCard.Indicator justifyContent="center" />
      </CheckboxCard.Control>
    </CheckboxCard.Root>
  );
};
