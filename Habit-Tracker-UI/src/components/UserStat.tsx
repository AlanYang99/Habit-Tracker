import { HStack, VStack, Icon, Stat } from "@chakra-ui/react";
import type { ReactNode } from "react";

interface UserStatProps {
  title: string;
  value: string | number;
  icon: ReactNode;
}

export default function UserStat({ title, value, icon }: UserStatProps) {
  return (
    <Stat.Root maxW="220px" maxH="150px" p="4" rounded="md">
      <HStack justify="space-between">
        <Icon color="fg.muted" size={["md", "lg", "xl", "2xl"]}>
          {icon}
        </Icon>
        <VStack>
          <Stat.Label textAlign="center">{title}</Stat.Label>
          <Stat.ValueText textAlign="center">{value}</Stat.ValueText>
        </VStack>
      </HStack>
    </Stat.Root>
  );
}
