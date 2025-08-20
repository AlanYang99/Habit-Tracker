import { Heading, Text } from "@chakra-ui/react";

import CustomCard from "./CustomCard";

export default function DateCard() {
  return (
    <CustomCard height="10vh">
      <Heading size="md" mb={2}>
        Today's Date
      </Heading>
      <Text fontSize="lg" color="gray.600">
        {new Date().toLocaleDateString()}
      </Text>
    </CustomCard>
  );
}
