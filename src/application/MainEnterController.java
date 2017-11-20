package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainEnterController {

	private ComboBox combo1;

	ObservableList<String> options =
			FXCollections.observableArrayList(
					"1", "2", "3", "4", "5", "6", "7", "8", "9"
			);

	public ComboBox combo2;
	public ComboBox combo3;
	public ComboBox combo4;

	@FXML
	private Button btnCustForm;
	@FXML
	private Button btnCustSignIn;
	@FXML
	private Button btnStaffSignIn;
	@FXML
	private Button btnCustLoginToBooking;
	@FXML
	private Button btnCustToTicketBooking;
	@FXML
	private Button btnCustToSeatBooking;
	@FXML
	private Button btnCustSignUpConfirmed;
	@FXML
	private Button btnGoBack;
	@FXML
	private Button btnToBookingSummary;
	@FXML
	private Button btnToPayment;
	@FXML
	private Button btnToConfirmPayment;
	@FXML
	private Button btnEmployeeLogin;

	// To store the sign up process
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private DatePicker dob;


	// To store the customer login process
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordTextField;
	
	//To store the employee login process
	@FXML
	private TextField staffUsername;
	@FXML
	private PasswordField staffPassword;

	static Stage stage;
	static Parent root;
	
	public void signUpTransition(ActionEvent event) throws IOException {

		try{

			ArrayList<Customer> customers = CustomerList.getInstance().getCustomerList();
			if (event.getSource() == btnCustForm) {
				stage = (Stage) btnCustForm.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("SignUpForm.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}

			if (event.getSource() == btnCustSignIn) {
				stage = (Stage) btnCustSignIn.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("CustomerSignIn.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}
			if (event.getSource() == btnStaffSignIn) {
				stage = (Stage) btnCustSignIn.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("StaffSignIn.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();


			}
			if (event.getSource() == btnCustLoginToBooking) {
				String usernameStr = usernameTextField.getText();
				String passwordStr = passwordTextField.getText();
				boolean foundUser = false;
				for (Customer customer : customers) {
					if ((customer.getUsername().equalsIgnoreCase(usernameStr))
							&& (customer.getPassword().equalsIgnoreCase(passwordStr))) {
						foundUser = true;
					}
				}


				if (!foundUser) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username / password - please try again");
					alert.showAndWait();
				}  else {
					stage = (Stage) btnCustLoginToBooking.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}

			}


			if (event.getSource() == btnCustSignUpConfirmed) {
				stage = (Stage) btnCustSignUpConfirmed.getScene().getWindow();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String firstName = txtFirstName.getText();
				String surname = txtSurname.getText();
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				String dobStr = dob.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));


				FileWriter fw = new FileWriter(new File("cinemaUsers.txt"), true);
				fw.write(firstName + "," + surname + "," + email + "," + username + "," + password + "," + dobStr + "\n");
				fw.close();
				Customer customer = new Customer();
				customer.setFirstName(firstName);
				customer.setSurname(surname);
				customer.setEmail(email);
				customer.setUsername(username);
				customer.setPassword(password);
				customer.setDob(dobStr);
				customers.add(customer);
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setHeaderText(null);
				alert.setContentText("Account has been created - you can now login");
				alert.showAndWait();
				root = FXMLLoader.load(getClass().getResource("MainEnter.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}


			if (event.getSource() == btnCustToTicketBooking) {
				stage = (Stage) btnCustToTicketBooking.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("TicketBooking.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}



			if (event.getSource() == btnEmployeeLogin) {
				String staffUsername = this.staffUsername.getText();
				String staffPassword = this.staffPassword.getText();
				if ((staffUsername.equalsIgnoreCase("admin")) &&
						(staffPassword.equalsIgnoreCase("password"))){
					stage = (Stage) btnEmployeeLogin.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("AddMovie.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username / password - please try again");
					alert.showAndWait();
				}
			}

		} catch (Exception e){
			e.printStackTrace();
		}

	}


		public void ticketBookingTransition(ActionEvent event) throws Exception {

			try{
				if (event.getSource() == btnCustToSeatBooking) {
					stage = (Stage) btnCustToSeatBooking.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("SeatBooking.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}

				if (event.getSource() == btnGoBack) {
					stage = (Stage) btnGoBack.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("FilmChoiceFrame.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			} catch (Exception e){
				e.printStackTrace();
			}


		}

		public void seatBookingTransition(ActionEvent event) throws Exception {
			try{

				if (event.getSource() == btnToBookingSummary) {
					stage = (Stage) btnToBookingSummary.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("OrderSummary.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();

				}

				if (event.getSource() == btnGoBack){
					stage = (Stage) btnGoBack.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("TicketBooking.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();

				}

			} catch (Exception e){
				e.printStackTrace();
			}

	}

	public void orderTransition(ActionEvent event) throws Exception {

			try {
				if (event.getSource() == btnToPayment) {
					stage = (Stage) btnToPayment.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();

				}

				if (event.getSource() == btnGoBack) {
					stage = (Stage) btnGoBack.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("SeatBooking.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			} catch (Exception e){
				e.printStackTrace();
			}

	}


	public void paymentTransition(ActionEvent event) throws Exception {


		try {
			if (event.getSource() == btnToConfirmPayment) {
				stage = (Stage) btnToConfirmPayment.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("PaymentConfirmed.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}

			if (event.getSource() == btnGoBack) {
				stage = (Stage) btnGoBack.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("OrderSummary.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}

}
