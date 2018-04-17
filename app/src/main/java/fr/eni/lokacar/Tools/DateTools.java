package fr.eni.lokacar.Tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public final class DateTools {
	/**
	    * Methode qui convertie une date au format Timestamp vers un format java.util.date
	*/
	public static Date getDateFromTimeStamp(Timestamp date){
		
		Date d = new Date();
		
		// Conversion timespamps -> localDateTime


		// Remplacement du caractère T pas " " afin de parser en java.util.date


		// Définition du format de date 
		SimpleDateFormat df2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.FRANCE);
		try {

			Date netDate = (new Date(Long.parseLong(date.toString())));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	
	}
	
	/**
	    * Methode qui convertie une date string ("yyyy-MM-dd" ) + un time au format (HH:mm) au format java.util.date
	*/
	
	public static Date getDateFromDayHour(String day, String hour) {
		
		Date d = new Date();
		
		// Construction de la date
		StringBuffer dateReconstruite = new StringBuffer();
		dateReconstruite.append(day);
		dateReconstruite.append(" ");
		dateReconstruite.append(hour);
		// Commenter cette ligne si les seconde sont passée en paramètre
		dateReconstruite.append(":00");
		
		// Definition du schéma de la date ( a adapter suivant le format reçu )
		SimpleDateFormat df2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
		try {
			
			d = df2.parse(dateReconstruite.toString());
		} catch (ParseException e) {
			
			// Exception a adapter en fonction du projet 
			/*BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.REGLE_FORMAT_DATE_ERREUR);
			throw be;*/
			e.printStackTrace();
		}
		return d;
		
	}
	

	/**
	    * Methode qui renvoi la partie horaire d'une java.util.date au format (HH:mm)
	*/
	public static String getHour(Date d){
		// Rajouter :ss au dateformat pour avoir les secondes
		SimpleDateFormat formater = new SimpleDateFormat("HH:mm",Locale.FRANCE);
		String heure = formater.format(d);
		//heure = heure.replace(':', 'h');

		return heure;

	}
	
	/**
	    * Methode qui renvoi la partie jour d'une java.util.date au format (dd/MM/YYY)
	*/
	public static String getDay(Date d){
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/YYYY");
		String day = formater.format(d);
		return day;	
	}
	
	/**
	    * Methode qui renvoi un timeStamp a partir d'une java.util.date
	*/
	public static Timestamp getDateTimestampFromUtilDate(Date d){
		Timestamp datetime = new Timestamp(d.getTime());
		return datetime;	
	}


}
