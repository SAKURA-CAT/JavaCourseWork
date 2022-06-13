import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BankSystem
 * @Author: cuny
 * @Date: 2022/6/13 15:56
 * @Description: 设计BankSystem类。您需要设计多个类。
 * 例如：您需要设计一个类BankAccount来对用户的银行帐户进行建模。
 * 可能是不同的银行账户(现金账户、信用账户...，帐户应该保存用户的姓名和余额，精确到最近的一分钱。
 * 用户应该能够在他/她的帐户上进行存取款，以及随时更改帐户名称。
 * 此外，系统还需要能够找出有多少银行账户被创造出来了。
 * 对于每个帐户，只有最后6笔交易应该能够按升序存储并打印。
 **/

public class BankSystem {
    private final List<BankHead> bankHeads = new ArrayList<>();

    public void createBankHead(String name){
        int id = bankHeads.size() + 1;
        bankHeads.add(new BankHead(Integer.toString(id), name));
    }

    public BankHead getBankHead(String name) throws IllegalArgumentException{
        for (BankHead obj: bankHeads){
            if(obj.getName().equals(name))
                return obj;
        }
        throw new IllegalArgumentException("该用户不存在！");
    }

    BankSystem(){}

    public static void main(String[] args) throws IllegalArgumentException {
        BankSystem bankSystem = new BankSystem();
        String userName = "cuny";
        bankSystem.createBankHead(userName);
        BankHead obj = bankSystem.getBankHead(userName);
        obj.addAccount("农业银行储蓄");
        obj.addAccount("交通银行储蓄");
        obj.recharge("农业银行储蓄", 100);
        obj.recharge("交通银行储蓄", 1000);
        obj.spend("农业银行储蓄", 2);
        obj.spend("农业银行储蓄", 10);
        obj.spend("交通银行储蓄", 90);
        obj.spend("农业银行储蓄", 50);
        System.out.print(obj);
        System.out.print(obj.publishConsumptionRecords());
    }
}
