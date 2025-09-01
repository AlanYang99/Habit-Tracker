import { Timeline, Text, Card } from "@chakra-ui/react";
import { LuCheck } from "react-icons/lu";

interface HabitLog {
  creationDate: Date;
  notes: string;
}

export default function UserActions({ habitlogs }: { habitlogs: HabitLog[] }) {
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
        {habitlogs.map((habitlog, key) => (
          <Timeline.Item mr={4} ml={4}>
            <Timeline.Connector>
              <Timeline.Separator />
              <Timeline.Indicator>
                <LuCheck />
              </Timeline.Indicator>
            </Timeline.Connector>
            <Timeline.Content>
              <Timeline.Title textStyle="sm">{habitlog.notes}</Timeline.Title>
              <Timeline.Description>
                {habitlog.creationDate.toLocaleString()}
              </Timeline.Description>
            </Timeline.Content>
          </Timeline.Item>
        ))}
      </Timeline.Root>
    </Card.Root>
  );
}
