import CustomCard from "./CustomCard";
import CustomCheckBox from "./CustomCheckBox";
import { Box, Heading, HStack, VStack } from "@chakra-ui/react";

export default function Habits() {
  return (
    <Box w="100%" h="100%" pt={4}>
      <CustomCard>
        <Box>
          <Heading as="h2" size="lg" mb={4} textAlign="start">
            Your Habits
          </Heading>
          <HStack justifyContent="space-between" mb={4} fieldSizing="wrap">
            <Box>
              <VStack gap={4} align="stretch">
                <CustomCheckBox
                  key={1}
                  habit="Habit 1: Drink 2 liters of water"
                />
                <CustomCheckBox
                  key={2}
                  habit="Habit 2: Drink 2 liters of water"
                />

                <CustomCheckBox
                  key={3}
                  habit="Habit 3: Drink 2 liters of water"
                />
                <CustomCheckBox
                  key={4}
                  habit="Habit 4: Drink 2 liters of water"
                />
              </VStack>
            </Box>
            <Box>
              <VStack gap={4} align="stretch">
                <CustomCheckBox
                  key={5}
                  habit="Habit 1: Drink 2 liters of water"
                />
                <CustomCheckBox
                  key={6}
                  habit="Habit 2: Drink 2 liters of water"
                />

                <CustomCheckBox
                  key={7}
                  habit="Habit 3: Drink 2 liters of water"
                />
                <CustomCheckBox
                  key={8}
                  habit="Habit 4: Drink 2 liters of water"
                />
              </VStack>
            </Box>
          </HStack>
        </Box>
      </CustomCard>
    </Box>
  );
}
