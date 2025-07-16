import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 지폐_접기 {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        List<Integer> billList = toList(bill);

        while (true) {
            int max = Math.max(billList.get(0), billList.get(1));
            int index = billList.indexOf(max);

            if ((billList.get(0) <= wallet[0] && billList.get(1) <= wallet[1])
                    || (billList.get(1) <= wallet[0] && billList.get(0) <= wallet[1])
            ) {
                break;
            } else {
                billList.set(index, max / 2);
                answer ++;
            }
        }

        return answer;
    }

    private List<Integer> toList(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toList());
    }
}
