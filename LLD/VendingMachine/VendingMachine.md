What is your State interface — what methods does it declare, and why those specifically?
What fields does your Context (VendingMachine class) hold?
Which state is responsible for returning change — and why?


interface State{
    public void insertCoin();
    public boolean selectProduct();
    public void dispenseProduct();
    public int getRefund();

}

Concrete states: 
IdleState - can only accept coin
CoinInsertedState - can accept coin or select product or get refund
DispenseState -  Can dispense the selected product
Maintainence State - Cannot do anything
OutOfStock State - cannot do anything