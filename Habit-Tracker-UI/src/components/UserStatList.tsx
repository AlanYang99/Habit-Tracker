import { HStack, Separator, Box, VStack, Progress } from "@chakra-ui/react";
import { InfoTip } from "@/components/ui/toggle-tip";
import UserStat from "./UserStat";
import type { ReactNode } from "react";

interface StatItem {
  title: string;
  value: string | number;
  icon: ReactNode;
}

interface UserProps {
  user: {
    gameSummary: {
      level: string | number;
      experience: number;
    };
  };
}

interface UserStatListProps {
  statList: StatItem[];
}

type Props = UserProps & UserStatListProps;

export default function UserStatList({ user, statList }: Props) {
  return (
    <Box
      mt={["20px", "35px", "50px"]}
      justifyContent="center"
      alignItems="center"
      display="flex"
      border="2px"
      background="white/60"
      rounded="4xl"
      h="200px"
      maxW={["400px", "500px", "600px"]}
    >
      <VStack>
        <HStack mr={["4px", "5px", "4px", "5px"]}>
          {statList?.map((stat, index) => (
            <Box key={index} display="flex" alignItems="center">
              {index !== 0 && (
                <Separator
                  orientation="vertical"
                  color="red.500"
                  height="100px"
                  mx="2"
                />
              )}
              <UserStat
                title={stat.title}
                value={stat.value}
                icon={stat.icon}
              />
            </Box>
          ))}
        </HStack>
        <Progress.Root
          width={["420px", "490px", "420px", "490px"]}
          defaultValue={user?.gameSummary?.experience / 322}
          colorPalette="green"
          variant="subtle"
          size="xl"
        >
          <HStack gap="2">
            <Progress.Label>
              Level {user?.gameSummary?.level}
              <InfoTip>Exp : {user?.gameSummary?.experience}/322</InfoTip>
            </Progress.Label>
            <Progress.Track flex="1">
              <Progress.Range />
            </Progress.Track>
            <Progress.ValueText>
              {((user?.gameSummary?.experience / 322) * 100).toFixed(2)}%
            </Progress.ValueText>
          </HStack>
        </Progress.Root>
      </VStack>
    </Box>
  );
}
