import { Avatar, Button, Card, For, Stack } from "@chakra-ui/react";

export default function Habit() {
  return (
    <Card.Root width="100%" variant={"subtle"} key={"subtle"}>
      <Card.Body gap="2">
        <Avatar.Root size="lg" shape="rounded">
          <Avatar.Image src="https://picsum.photos/200/300" />
          <Avatar.Fallback name="Nue Camp" />
        </Avatar.Root>
        <Card.Title mb="2">Nue Camp</Card.Title>
        <Card.Description>
          This is the card body. Lorem ipsum dolor sit amet, consectetur
          adipiscing elit.
        </Card.Description>
      </Card.Body>
      <Card.Footer justifyContent="flex-end">
        <Button variant="outline">View</Button>
        <Button>Join</Button>
      </Card.Footer>
    </Card.Root>
  );
}
