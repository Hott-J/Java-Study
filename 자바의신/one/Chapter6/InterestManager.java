package Chapter6;

public class InterestManager {
    public double getInterestRate(int day) {
        return day >= 365 ? 0.56 : 181 <= day && day < 364 ? 0.2 : 91 <= day && day < 180 ? 0.1 : 1 <= day && day < 90 ? 0.05 : 0;
    }

    public double calculateAmount(int day, long amount) {
        return getInterestRate(day) * amount + amount;
    }

    public static void main(String[] args) {
        InterestManager interestManager = new InterestManager();
        System.out.println(interestManager.calculateAmount(1, 1000000));
    }
}
