import { Card, Box } from "@chakra-ui/react";

import type { ReactNode } from "react";

type CustomCardProps = {
  align?: string | undefined;
  children: ReactNode;
};

export default function CustomCard({
  align = "center",
  children,
}: CustomCardProps) {
  return (
    <Card.Root
      width="100%"
      height="100%"
      bg="whiteAlpha.900"
      borderRadius="md"
      shadow={"md"}
      border="0"
    >
      <Card.Body
        display="flex"
        w="100%"
        h="100%"
        gap="4"
        alignItems={align}
        justifyContent={align}
      >
        <Card.Description
          flex="1"
          display="flex"
          alignItems={align}
          justifyContent={align}
          w="100%"
          h="100%"
          gap={6} // space between heading and icon
        >
          {children}
        </Card.Description>
      </Card.Body>
    </Card.Root>
  );
}
