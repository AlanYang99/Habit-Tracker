import { CheckboxCard } from "@chakra-ui/react";

type CustomCheckBoxProps = {
  key: React.Key;
  habit: string;
};

export default function CustomCheckBox({ key, habit }: CustomCheckBoxProps) {
  return (
    <CheckboxCard.Root
      defaultChecked={false}
      key={key}
      variant="subtle"
      colorPalette="green"
      boxShadow="md"
      size="sm"
      rounded={"md"}
    >
      <CheckboxCard.HiddenInput />
      <CheckboxCard.Control>
        <CheckboxCard.Label>{habit}</CheckboxCard.Label>
        <CheckboxCard.Indicator />
      </CheckboxCard.Control>
    </CheckboxCard.Root>
  );
}
