import { Text, Timeline, Box } from "@chakra-ui/react";
import { LuCheck, LuPackage, LuShip } from "react-icons/lu";
import CustomCard from "./CustomCard";

export default function CustomTimeline() {
  return (
    <Box w="100%" h="100%" pl={4} pr={4}>
      <CustomCard align="start">
        <Demo />
      </CustomCard>
    </Box>
  );
}

const Demo = () => {
  return (
    <Timeline.Root maxW="400px">
      <Timeline.Item>
        <Timeline.Connector>
          <Timeline.Separator />
          <Timeline.Indicator>
            <LuShip />
          </Timeline.Indicator>
        </Timeline.Connector>
        <Timeline.Content>
          <Timeline.Title>Product Shipped</Timeline.Title>
          <Timeline.Description>13th May 2021</Timeline.Description>
          <Text textStyle="sm">
            We shipped your product via <strong>FedEx</strong> and it should
            arrive within 3-5 business days.
          </Text>
        </Timeline.Content>
      </Timeline.Item>

      <Timeline.Item>
        <Timeline.Connector>
          <Timeline.Separator />
          <Timeline.Indicator>
            <LuCheck />
          </Timeline.Indicator>
        </Timeline.Connector>
        <Timeline.Content>
          <Timeline.Title textStyle="sm">Order Confirmed</Timeline.Title>
          <Timeline.Description>18th May 2021</Timeline.Description>
        </Timeline.Content>
      </Timeline.Item>
    </Timeline.Root>
  );
};
