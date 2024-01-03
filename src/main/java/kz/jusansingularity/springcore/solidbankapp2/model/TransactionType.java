package kz.jusansingularity.springcore.solidbankapp2.model;

public enum TransactionType {

    WITHDRAW("WITHDRAW"),
    DEPOSIT("DEPOSIT");
    private String transactionType;
    TransactionType(String transactionType){
        this.transactionType = transactionType;
    }
    public String getCode(){ return transactionType;}

}
