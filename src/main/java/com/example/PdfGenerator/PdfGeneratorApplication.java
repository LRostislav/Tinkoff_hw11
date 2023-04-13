package com.example.PdfGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.util.*;

@SpringBootApplication
public class PdfGeneratorApplication {
	public static void main(String[] args) throws Exception {
		String dest = "C:/itextExamples/addingTable.pdf";
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document doc = new Document(pdf, PageSize.A4.rotate());

		float sizeCol = 75F;
		int sizeFont = 6;

		float [] pointColumnWidths = {sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol,
				sizeCol, sizeCol, sizeCol, sizeCol, sizeCol, sizeCol};
		Table table = new Table(pointColumnWidths);

		PdfFont russian = PdfFontFactory.createFont(
				"src/main/resources/fonts/FreeSans/freesans.ttf", "CP1251", true);

		List<String> row = new ArrayList<>();
		row.add("Имя");
		row.add("Фамилия");
		row.add("Отчество");
		row.add("Возраст");
		row.add("Пол");
		row.add("Дата рождения");
		row.add("Место рождения");
		row.add("Индекс");
		row.add("Страна");
		row.add("Область");
		row.add("Город");
		row.add("Улица");
		row.add("Дом");
		row.add("Квартира");
		addRow(table, sizeFont, row);


		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите количество человек: ");
		int number = scanner.nextInt();

		for (int i = 0; i<number; i++){
			addRow(table, sizeFont, generateData(i));
		}

		doc.setFont(russian);
		doc.add(table);

		doc.close();
		System.out.println("Файл создан. Путь: C:/itextExamples/addingTable.pdf");
	}


	public static void addRow(Table table, int sizeFont, List<String> row){
		for (int i = 0; i < row.toArray().length; i++) {
			table.addCell(new Cell().add(row.get(i)).setFontSize(sizeFont));
		}
	}


	public static List<String> generateData(int number){
		List<String> newRow = new ArrayList<>();

		Random rand = new Random();
		int randomIndex;

		if (number % 2 == 0) {
			repositoryMan repository = new repositoryMan();

			randomIndex = rand.nextInt(repository.nameList.size());
			newRow.add(repository.nameList.get(randomIndex));

			randomIndex = rand.nextInt(repository.surnameList.size());
			newRow.add(repository.surnameList.get(randomIndex));

			randomIndex = rand.nextInt(repository.patronymicList.size());
			newRow.add(repository.patronymicList.get(randomIndex));
		} else {
			repositoryWoman repository = new repositoryWoman();

			randomIndex = rand.nextInt(repository.nameList.size());
			newRow.add(repository.nameList.get(randomIndex));

			randomIndex = rand.nextInt(repository.surnameList.size());
			newRow.add(repository.surnameList.get(randomIndex));

			randomIndex = rand.nextInt(repository.patronymicList.size());
			newRow.add(repository.patronymicList.get(randomIndex));
		}


		int age = rand.nextInt(70)+10;
		newRow.add(Integer.toString(age));

		if (number % 2 == 0) {
			newRow.add("МУЖ");
		} else {
			newRow.add("ЖЕН");
		}

		int randDay = rand.nextInt(31);
		int randMonth = rand.nextInt(12)+1;
		int Year = 2023 - age;
		newRow.add(Integer.toString(randDay) + '.' + Integer.toString(randMonth) + '.' + Integer.toString(Year));

		repository repository1 = new repository();

		randomIndex = rand.nextInt(repository1.cityList.size());
		newRow.add(repository1.cityList.get(randomIndex));

		int randIndex = rand.nextInt(900000)+100000;
		newRow.add(Integer.toString(randIndex));

		newRow.add("Россия");

		randomIndex = rand.nextInt(repository1.areaList.size());
		newRow.add(repository1.areaList.get(randomIndex));

		randomIndex = rand.nextInt(repository1.cityList.size());
		newRow.add(repository1.cityList.get(randomIndex));

		randomIndex = rand.nextInt(repository1.streetList.size());
		newRow.add(repository1.streetList.get(randomIndex));

		int randHouse = rand.nextInt(150)+1;
		newRow.add(Integer.toString(randHouse));

		int randApartment = rand.nextInt(150)+1;
		newRow.add(Integer.toString(randApartment));

		return newRow;
	}
}


class repositoryMan{
	List<String> nameList = Arrays.asList("Белолипецкий", "Кулинский", "Кортылёв", "Рыбаков", "Ганеев",
			"Хасанов", "Тарасов", "Лебедев", "Лысенков", "Тямаев", "Круг", "Вакуленко", "Корж",
			"Салтыков", "Расторгуев");
	List<String> surnameList = Arrays.asList("Алексей", "Антон", "Дмитрий", "Павел", "Руслан",
			"Артур", "Андрей", "Никита", "Ростислав", "Фирдус", "Михаил", "Василий", "Максим",
			"Виктор", "Николай");
	List<String> patronymicList = Arrays.asList("Алексеевич", "Антонович", "Дмитриевич", "Павлович",
			"Русланович", "Артурович", "Андреевич", "Никитович", "Ростиславович", "Фирдус оглы",
			"Михайлович", "Васильевич", "Максимович", "Викторович", "Николаевич");
}


class repositoryWoman{
	List<String> nameList = Arrays.asList("Белолипецкий", "Кулинский", "Кортылёв", "Рыбаков", "Ганеев",
			"Хасанов", "Тарасов", "Лебедев", "Лысенков", "Тямаев", "Круг", "Вакуленко", "Корж",
			"Салтыков", "Расторгуев");
	List<String> surnameList = Arrays.asList("Алексей", "Антон", "Дмитрий", "Павел", "Руслан",
			"Артур", "Андрей", "Никита", "Ростислав", "Фирдус", "Михаил", "Василий", "Максим",
			"Виктор", "Николай");
	List<String> patronymicList = Arrays.asList("Алексеевич", "Антонович", "Дмитриевич", "Павлович",
			"Русланович", "Артурович", "Андреевич", "Никитович", "Ростиславович", "Фирдус оглы",
			"Михайлович", "Васильевич", "Максимович", "Викторович", "Николаевич");
}


class repository{
	List<String> cityList = Arrays.asList("Бугульма", "Лениногорск", "Альметьевск", "Казань", "Москва",
			"Нижнекамск", "Октябрьск", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Уфа",
			"Самара", "Челябинск", "Красноярск", "Пермь");
	List<String> areaList = Arrays.asList("Орловская", "Ростовская", "Магаданская", "Пермская", "Белгородская",
			"Томская", "Тверская", "Тамбовская", "Республика Татарстан", "Иркутская",
			"Республика Башкортостан", "Челябинская", "Самарская", "Саратовская", "Ульяновская");
	List<String> streetList = Arrays.asList("Баумана", "Островского", "Московская", "Кремлевская",
			"Дзержинского", "Ленина", "Саид-Галеева", "Пушкина", "Габдуллы Тукая", "Мусы Джалиля",
			"Гвардейская", "Карла Маркса", "Правобулачная", "Никольская", "Петровка");
}