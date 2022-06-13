import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: BankHead
 * @Author: cuny
 * @Date: 2022/6/13 16:37
 * @Description: 银行户主
 **/

public class BankHead {
    private static class BankAccounts{
        private final List<BankAccount> accounts = new ArrayList<>();


        public Integer getAccountsLength(){
            return accounts.size();
        }

        // 判断账户名称是否已经添加，是则返回true
        public boolean alreadyExists(String accountName){
            for (BankAccount obj: accounts){
                if (obj.getAccountName().equals(accountName)){
                    return true;
                }
            }
            return false;
        }

        // 寻找账户，返回该账户本身
        public BankAccount findAccountObj(String accountName) throws IllegalArgumentException{
            for (BankAccount obj: accounts){
                if (obj.getAccountName().equals(accountName)){
                    return obj;
                }
            }
            throw new IllegalArgumentException("该账户不存在!");
        }

        // 寻找账户，返回该账户的index
        public Integer findAccountIndex(String accountName) throws IllegalArgumentException{
            for (BankAccount obj: accounts){
                if (obj.getAccountName().equals(accountName)){
                    return accounts.indexOf(obj);
                }
            }
            throw new IllegalArgumentException("该账户不存在!");
        }

        @Override
        public String toString(){
            StringBuilder content = new StringBuilder();
            for (BankAccount obj: accounts){
                content.append("    ").append(obj.toString()).append("\n");
            }
            return content.toString();
        }
    }

    private static class ConsumptionRecord implements Comparable<ConsumptionRecord>{
        public String accountName;
        public double money;

        ConsumptionRecord(String accountName, double money){
            this.accountName = accountName;
            this.money = money;
        }

        @Override
        public String toString() {
            return accountName + "消费: " + money;
        }

        @Override
        public int compareTo(ConsumptionRecord o) {
            return -Double.compare(money, o.money);
        }
    }

    private final BankAccounts bankAccounts = new BankAccounts();

    private final String name;

    private final String id;

    private final List<ConsumptionRecord> consumptionRecords = new ArrayList<>();

    BankHead(String id, String name){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    // 添加账户
    public void addAccount(String accountName) throws IllegalArgumentException{
        if (bankAccounts.alreadyExists(accountName)){
            throw new IllegalArgumentException("该账户已经存在!");
        }
        bankAccounts.accounts.add(new BankAccount(accountName));
    }

    // 删除账户
    public void delete(String accountName) throws IllegalArgumentException{
        BankAccount deleteOne = bankAccounts.findAccountObj(accountName);
        bankAccounts.accounts.remove(deleteOne);
    }

    // 给账户充钱
    public void recharge(String accountName, double money) throws IllegalArgumentException{
        bankAccounts.findAccountObj(accountName).recharge(money);
    }

    // 消费账户
    public void spend(String accountName, double money) throws IllegalArgumentException{
        bankAccounts.findAccountObj(accountName).spend(money);
        consumptionRecords.add(new ConsumptionRecord(accountName, money));
    }

    // 查询某个账户的余额
    public double getBalance(String accountName, double money) throws IllegalArgumentException{
        return bankAccounts.findAccountObj(accountName).getBalance();
    }

    public String toString(){
        StringBuilder content = new StringBuilder();
        content.append("Dear ").append(getName()).append(", ");
        if (bankAccounts.getAccountsLength() != 0)
            content.append("the accounts you have include:\n").append(bankAccounts);
        else
            content.append("you don't have any accounts.");
        return content.toString();
    }

    public String publishConsumptionRecords() {
        int consumptionRecordsLength= consumptionRecords.size();
        StringBuilder content = new StringBuilder("You have a total of [" + consumptionRecordsLength + "] spending records.");
        Collections.sort(consumptionRecords); // 升序
        if (consumptionRecordsLength <= 0)
            return "You haven't spent money recently.";
        else if (consumptionRecordsLength <= 6) {
            content.append("\nHere are the [").append(consumptionRecordsLength).append("]consumption records:").append("\n");
            for (ConsumptionRecord obj: consumptionRecords){
                content.append("    ").append(obj).append("\n");
            }
            return content.toString();
        }else{
            content.append("\nThe following are the first [6] consumption records").append("\n");
            for (ConsumptionRecord obj: consumptionRecords){
                content.append("    ").append(obj).append("\n");
                if(consumptionRecords.indexOf(obj) == 5){
                    break;
                }
            }
            return content.toString();
        }
    }
}
