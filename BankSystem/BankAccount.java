/**
 * @ClassName: BankAccountAbstract
 * @Author: cuny
 * @Date: 2022/6/13 16:13
 * @Description: 银行账户
 **/

public class BankAccount {
    private String accountName;
    private double accountBalance;

    BankAccount(String accountName) throws IllegalArgumentException{
        if (accountName.length() == 0)
            throw new IllegalArgumentException("非法账户名称!");
        this.accountName = accountName;
        accountBalance = 0.;
    }

    // 获取账户类型，如果账户信息为空会抛出空指针异常
    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName) throws IllegalArgumentException{
        if (accountName.length() == 0){
            throw new IllegalArgumentException("非法账户名称!");
        }
        this.accountName = accountName;
    }

    // 获取当前的账户余额，如果余额为负数或者0会抛出自定义异常
    public double getBalance() throws IllegalArgumentException {
        if (accountBalance > 0)
            return 0;
        throw new IllegalArgumentException("余额不足!");
    }

    // 账户消费，如果消费失败（没钱了）会返回自定义异常
    public void spend(double money) throws IllegalArgumentException {
        if (accountBalance < money)
            throw new IllegalArgumentException("余额不足!");
        accountBalance -= money;
    }

    // 账户充值
    public void recharge(double money){
        accountBalance += money;
    }

    @Override
    public String toString() {
        return accountName + ": " + accountBalance + " YUAN";
    }
}
