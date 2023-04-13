package com.example.PdfGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import java.util.*;

import com.itextpdf.layout.property.TextAlignment;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PdfGeneratorApplication {
	public static void main(String[] args) throws Exception {
		String dest = "C:/itextExamples/Table.pdf";
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document doc = new Document(pdf, PageSize.A4.rotate());

		float sizeCol = 75F;
		int sizeFont = 6;

		float [] pointColumnWidths = {sizeCol-18F, sizeCol-18F, sizeCol-10F, sizeCol-25F, sizeCol-30F, sizeCol,
				sizeCol+10F, sizeCol-25F, sizeCol, sizeCol+15F, sizeCol+15F, sizeCol, sizeCol-25F, sizeCol-25F};
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
		for (int i = 0; i < row.toArray().length; i++) {
			table.addCell(new Cell().add(row.get(i)).setHeight(15).setPadding(0).setMargin(0)
					.setPaddingLeft(2).setTextAlignment(TextAlignment.LEFT).setFontSize(sizeFont));
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите количество человек (1 <= n <= 30): ");
		int number = scanner.nextInt();
		while (number < 1 || number > 30) {
			System.out.println("Некорректно введенные данные");
			System.out.println("Введите количество человек (1 <= n <= 30): ");
			number = scanner.nextInt();
		}

		for (int i = 0; i<number; i++){
			addRow(table, sizeFont, generateData(i));
		}

		doc.setFont(russian);
		doc.add(table);

		doc.close();
		System.out.println("Файл создан. Путь: C:/itextExamples/Table.pdf");
	}


	public static void addRow(Table table, int sizeFont, List<String> row){
		for (int i = 0; i < row.toArray().length; i++) {
			switch (i) {
				case 0, 1, 2, 4, 6, 8, 9, 10, 11 ->
						table.addCell(new Cell().add(row.get(i)).setHeight(15).setPadding(0).setMargin(0)
								.setPaddingLeft(2).setTextAlignment(TextAlignment.LEFT).setFontSize(sizeFont));
				case 3, 5, 7, 12, 13 ->
						table.addCell(new Cell().add(row.get(i)).setHeight(15).setPadding(0).setMargin(0)
								.setPaddingRight(2).setTextAlignment(TextAlignment.RIGHT).setFontSize(sizeFont));
			}
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


		int randDay = rand.nextInt(31)+1;
		int randMonth = rand.nextInt(12)+1;
		int Year = 2023 - age;
		if (randMonth > 4 || (randMonth == 4 && randDay > 15)) {
			Year--;
		}
		String randDayStr;
		String randMonthStr;
		if (randDay < 10) {
			randDayStr = '0' + Integer.toString(randDay);
		} else {
			randDayStr = Integer.toString(randDay);
		}

		if (randMonth < 10) {
			randMonthStr = '0' + Integer.toString(randMonth);
		} else {
			randMonthStr = Integer.toString(randMonth);
		}

		newRow.add(randDayStr + '.' + randMonthStr + '.' + Year);


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
	List<String> nameList = Arrays.asList("Алексей", "Антон", "Дмитрий", "Павел", "Руслан",
			"Артур", "Андрей", "Никита", "Ростислав", "Фирдус", "Михаил", "Василий", "Максим",
			"Виктор", "Николай");
	List<String> surnameList = Arrays.asList("Белолипецкий", "Кулинский", "Кортылёв", "Рыбаков", "Ганеев",
			"Хасанов", "Тарасов", "Лебедев", "Лысенков", "Тямаев", "Круг", "Вакуленко", "Корж",
			"Салтыков", "Расторгуев");
	List<String> patronymicList = Arrays.asList("Алексеевич", "Антонович", "Дмитриевич", "Павлович",
			"Русланович", "Артурович", "Андреевич", "Никитович", "Ростиславович", "Фирдус оглы",
			"Михайлович", "Васильевич", "Максимович", "Викторович", "Николаевич");
}


class repositoryWoman{
	List<String> nameList = Arrays.asList("Марина", "Полина", "Елизавета", "Алена", "Софья", "Анна", "Алиса",
			"Ева", "Алина", "Екатерина", "Ксения", "Наталья", "Татьяна", "Светлана", "Анфиса");
	List<String> surnameList = Arrays.asList("Мерзлякова", "Пронькина", "Воронина", "Абрамова", "Громова",
			"Журавлева", "Захарова", "Зотова", "Ермакова", "Гришина", "Дмитриева", "Емельянова", "Князева",
			"Николаева", "Давыдова");
	List<String> patronymicList = Arrays.asList("Алексеевна", "Антоновна", "Дмитриевна", "Павловна", "Руслановна",
			"Артуровна", "Андреевна", "Никитовна", "Ростиславовна", "Фирдус кызы", "Михайловна", "Васильевна",
			"Максимовна", "Викторовна", "Николаевна");
}


class repository{
	List<String> cityList = Arrays.asList("Бугульма", "Лениногорск", "Альметьевск", "Казань", "Москва",
			"Нижнекамск", "Октябрьский", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Уфа",
			"Самара", "Челябинск", "Красноярск", "Пермь");
	List<String> areaList = Arrays.asList("Орловская", "Ростовская", "Магаданская", "Пермская", "Белгородская",
			"Томская", "Тверская", "Тамбовская", "Татарстан", "Иркутская",
			"Башкортостан", "Челябинская", "Самарская", "Саратовская", "Ульяновская");
	List<String> streetList = Arrays.asList("Баумана", "Островского", "Московская", "Кремлевская",
			"Дзержинского", "Ленина", "Саид-Галеева", "Пушкина", "Габдуллы Тукая", "Мусы Джалиля",
			"Гвардейская", "Карла Маркса", "Правобулачная", "Никольская", "Петровка");
}
