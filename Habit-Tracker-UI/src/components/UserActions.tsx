import { Timeline, Text, Card } from "@chakra-ui/react";
import { LuShip, LuCheck } from "react-icons/lu";

export default function UserActions() {
  return (
    <Card.Root
      maxW="sm"
      background="white/60"
      rounded="4xl"
      border="2px"
      h="600px"
      width="450px"
      display="flex"
      flexDirection="column"
      alignItems="center"
      mb={4}
      mr={4}
      ml={4}
    >
      <Card.Title mt={7}>Recent User Actions</Card.Title>
      <Timeline.Root maxW="600px" m={10}>
        <Timeline.Item mr={4} ml={4}>
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

        <Timeline.Item mr={4} ml={4}>
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
    </Card.Root>
  );
}
