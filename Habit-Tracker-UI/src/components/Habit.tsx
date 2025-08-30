import {
  CheckboxCard,
  Circle,
  Dialog,
  Status,
  Button,
  Portal,
  CloseButton,
} from "@chakra-ui/react";

type HabitProps = {
  key: React.Key;
  habit: {
    name: string;
    description: string;
    completionStatus: boolean;
  };
};

export default function Habit({ key, habit }: HabitProps) {
  return (
    <CheckboxCard.Root
      key={key}
      variant="outline"
      colorPalette="green"
      boxShadow="md"
      size="sm"
      rounded="3xl"
      width="100%"
      m={4}
    >
      {/* <CheckboxCard.HiddenInput /> */}
      <CheckboxCard.Control
        display="grid"
        gridTemplateColumns={{
          base: "10% 50% 20% 15%",
          lg: "10% 40% 25% 20%",
          xl: "10% 40% 25% 20%",
          "2xl": "10% 50% 20% 15%",
        }}
        alignItems="center"
        gap={2}
        p={2}
      >
        <CheckboxCard.Content justifyContent="center">
          <Circle
            size="40px"
            bg="teal.50"
            color="teal.500"
            border="2px solid"
            borderColor="teal.300"
          ></Circle>
        </CheckboxCard.Content>

        <CheckboxCard.Content textAlign="left">
          <CheckboxCard.Label>{habit.name}</CheckboxCard.Label>
          <CheckboxCard.Description>
            {habit.description}
          </CheckboxCard.Description>
        </CheckboxCard.Content>

        <Status.Root
          size="lg"
          colorPalette={habit.completionStatus ? "green" : "red"}
        >
          <Status.Indicator />
          {habit.completionStatus ? "Complete" : "Incomplete"}
        </Status.Root>

        <Dialog.Root>
          <Dialog.Trigger asChild>
            <Button
              variant="outline"
              size="md"
              rounded="xl"
              disabled={habit.completionStatus}
            >
              Complete Habit
            </Button>
          </Dialog.Trigger>
          <Portal>
            <Dialog.Backdrop />
            <Dialog.Positioner>
              <Dialog.Content>
                <Dialog.Header>
                  <Dialog.Title>Complete Habit</Dialog.Title>
                </Dialog.Header>
                <Dialog.Body>
                  <p>
                    Once you complete the habit, you cannot revert the status
                  </p>
                </Dialog.Body>
                <Dialog.Footer>
                  <Dialog.ActionTrigger asChild>
                    <Button variant="outline">Cancel</Button>
                  </Dialog.ActionTrigger>
                  <Button>Complete</Button>
                </Dialog.Footer>
                <Dialog.CloseTrigger asChild>
                  <CloseButton size="sm" />
                </Dialog.CloseTrigger>
              </Dialog.Content>
            </Dialog.Positioner>
          </Portal>
        </Dialog.Root>
      </CheckboxCard.Control>
    </CheckboxCard.Root>
  );
}
