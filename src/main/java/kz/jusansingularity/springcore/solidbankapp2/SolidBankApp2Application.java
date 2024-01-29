package kz.jusansingularity.springcore.solidbankapp2;


import kz.jusansingularity.springcore.solidbankapp2.DAO.MemoryTransactionDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.Transaction;
import kz.jusansingularity.springcore.solidbankapp2.model.TransactionDepositCLI;
import kz.jusansingularity.springcore.solidbankapp2.model.TransactionWithdrawCLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountBasicCLI;
import kz.jusansingularity.springcore.solidbankapp2.service.MyCLI;

@SpringBootApplication
public class SolidBankApp2Application implements CommandLineRunner {
	@Value("${help.message}")
	String helpMessage;

	@Value("${welcome.message}")
	String welcomeMessage;

	@Value("${enter.operation}")
	String enterOperation;
	private final ApplicationContext context;

	@Autowired
	public SolidBankApp2Application(ApplicationContext context) {
		this.context = context;
	}

	public static void main(String[] args) {
		SpringApplication.run(SolidBankApp2Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		boolean running = true;
		String clientID = "1";

		MyCLI myCLI = context.getBean(MyCLI.class);
		AccountBasicCLI accountBasicCLI = context.getBean(AccountBasicCLI.class);
		TransactionDepositCLI transactionDepositCLI = context.getBean(TransactionDepositCLI.class);
		TransactionWithdrawCLI transactionWithdrawCLI = context.getBean(TransactionWithdrawCLI.class);

		MemoryTransactionDAO memoryTransactionDAO = context.getBean(MemoryTransactionDAO.class);


		System.out.println(welcomeMessage);
		System.out.println(enterOperation);
		System.out.println(helpMessage);

		while(running){
			System.out.print(enterOperation);
			try {
				switch(myCLI.getScanner().nextLine()){

					case "1":
						accountBasicCLI.getAccounts(clientID);
						break;

					case "2":
						System.out.println("Choose account type: [ CHECKING, SAVING, FIXED ]");
						accountBasicCLI.createAccountRequest(clientID);
						break;

					case "3":
						transactionDepositCLI.depositMoney(clientID);
						break;

					case "4":
						transactionWithdrawCLI.withdrawMoney(clientID);
						break;

					case "5":
						for(Transaction transaction: memoryTransactionDAO.findAll()) {
							System.out.println(transaction);
						};
						break;

					case "6":
						System.out.printf(helpMessage);
						break;

					case "7":
						System.out.printf("Application Closed\n");
						running = false;
						break;

					default:
						System.out.printf("Command not recognized!\n");
						break;
				}
			} catch (Exception e) {
				System.out.println("Error has happened " + e.getMessage());
			}
		}
		myCLI.getScanner().close();
	}

}
