/**
 * 
 */
package org.springframework.samples.petclinic.selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.samples.petclinic.SelenideBase;

/**
 * @author setupuser
 *
 */
public class PetClinicTest1 extends SelenideBase {

	private String appName;

	@Test
	public void test() {
		int id = new Random().nextInt(10000);

		//open("http://18.209.204.157:8080/");
		open("http://" + getEnvValue("ALB_HOST") + "/");

		System.out.println("start");

		// Find OWNERESリンク押下
		$(By.xpath("//*[@id=\"main-navbar\"]/ul/li[3]/a")).click();
		System.out.println("1.Find OWNERESリンク押下");

		// [Find Owners]画面
		// Add Ownerボタンクリック
		$(By.xpath("/html/body/div/div/a")).click();
		System.out.println("2.Add Ownerボタンクリック");

		// [Add Owner]画面
		$("#firstName").val("Lupin");
		$("#lastName").val(String.valueOf(id) + "th");
		$("#address").val("300 Madison Ave 4th Floor");
		$("#city").val("New York");
		$("#telephone").val("0351662500");
		// Add Ownerボタンクリック
		$(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button")).click();
		System.out.println("3.Add Ownerボタンクリック2");

		// [Owner Information]画面
		// Add New Petボタンクリック
		$(By.xpath("/html/body/div/div/a[2]")).click();
		System.out.println("4.Add New Petボタンクリック");

		// [New Pet]画面
		$("#name").val("Pochi" + id);
		$("#birthDate").val("1990-01-01");
		$("#type").selectOption("dog");
		// Add Petボタンクリック
		$(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
		System.out.println("5.Add Petボタンクリック");

		// [Owner Information]画面
		// Add Visitリンク押下
		$(By.xpath("/html/body/div/div/table[2]/tbody/tr/td[2]/table/tbody/tr/td[2]/a")).click();
		System.out.println("6.Add Visitリンク押下");

		// [New Visit]画面
		$("#description").val("初回訪問");
		$(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();

		// [Owner Information]画面
		// FIND OWNERESリンク押下
		$(By.xpath("//*[@id=\"main-navbar\"]/ul/li[3]/a")).click();
		System.out.println("7.FIND OWNERESリンク押下");

		// [Find Owneres]画面
		$("#lastName").val(String.valueOf(id) + "th");
		// Find Ownerボタン押下
		$(By.xpath("//*[@id=\"search-owner-form\"]/div[2]/div/button")).click();
		System.out.println("8.Find Ownerボタン押下");

		// [Ownere Information]画面
		$("body").shouldHave(text("Pochi" + id));
		System.out.println("End");

	}

}
