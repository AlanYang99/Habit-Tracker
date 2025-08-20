import CustomCard from "./CustomCard";
import {
  Heading,
  Progress,
  Box,
  HStack,
  VStack,
  Text,
  Spacer,
} from "@chakra-ui/react";
import { InfoTip } from "@/components/ui/toggle-tip";
import { SlBadge } from "react-icons/sl";

export default function GameStatsSummary() {
  return (
    <Box w="100%" h="100%" pr={4} pl={4}>
      <CustomCard>
        <VStack align="stretch">
          <Heading as="h2" size="lg" mb={4} textAlign="center">
            Stats Summary
          </Heading>
          <VStack>
            <Box>
              <Progress.Root
                width="37vw"
                defaultValue={40}
                colorPalette="green"
                variant="subtle"
                size="xl"
              >
                <HStack gap="2">
                  <Progress.Label>
                    Level 1<InfoTip>Exp : 211/322</InfoTip>
                  </Progress.Label>
                  <Progress.Track flex="1">
                    <Progress.Range />
                  </Progress.Track>
                  <Progress.ValueText>40%</Progress.ValueText>
                </HStack>
              </Progress.Root>
            </Box>
            <HStack justifyContent="space-center" width="50%">
              <Text>Level : 1</Text>
              <Spacer />
              <Text display={"inline-flex"} alignItems="center">
                <SlBadge color="gold" style={{ margin: "4px" }} /> : 1
              </Text>
            </HStack>
          </VStack>
        </VStack>
      </CustomCard>
    </Box>
  );
}
