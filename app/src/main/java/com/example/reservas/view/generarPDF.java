package com.example.reservas.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import com.example.reservas.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class generarPDF {

    public void generatePDF(Context context, objSalud salud) throws IOException, DocumentException {
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(downloadsDir, "FichaSalud-"+salud.getAyn() +".pdf");
        FileOutputStream fos=new FileOutputStream(file);
        // Create a new document with PDF version 1.7
        Document doc= new Document();
        doc.setPageSize(PageSize.A4);
        PdfWriter.getInstance(doc,fos);
        doc.open();
        doc.setMargins(5, 5, 5, 5); // 72 puntos por pulgada

        // Add image to the left of text
        Drawable d = context.getDrawable(R.drawable.paraisoyung);
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Image image = Image.getInstance(byteArray);
        image.scaleAbsolute(70f, 50f);
        image.setAbsolutePosition(20, 770);
        doc.add(image);
        Paragraph text = new Paragraph("    Legajo PA-017-19\n" +
                "   Ley Provincial N° 6041/17 “Turismo Activo”\n");
        text.setAlignment(Element.ALIGN_RIGHT);
        doc.add(text);
        Paragraph o = new Paragraph();
        o.setSpacingBefore(10);

// Agregar el objeto Paragraph antes de la tabla
        doc.add(o);
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font font = new Font(Font.FontFamily.HELVETICA, 10f);
        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("FICHA DECLARACIÓN DE SALUD", titleFont));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell1.setBorder(Rectangle.NO_BORDER);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table1.addCell(cell1);
        doc.add(table1);


        Paragraph cuerpo = new Paragraph("Los datos proporcionados serán de uso confidencial y exclusivo de los Programas Turísticos que Opera la Empresa de Turismo Activo “Paraíso de las Yungas”. Los mismos son considerados como Declaración Jurada. ", font);
        cuerpo.setAlignment(Element.ALIGN_LEFT);
        doc.add(cuerpo);

      doc.add(o);

        PdfPTable table = new PdfPTable(4); // 4 es el número máximo de columnas que tendrá la tabla
        table.setWidthPercentage(100);
// Primera fila
        PdfPCell cell = new PdfPCell(new Phrase("Apellido y Nombre: "+salud.getAyn(),font));
        cell.setColspan(4); // Indicamos que esta celda ocupa las 4 columnas de la tabla
        table.addCell(cell);

// Segunda fila
      table.addCell(new PdfPCell(new Phrase("Fecha Nac: "+salud.getFnac(),font)));
       table.addCell(new PdfPCell(new Phrase("DNI/Pasaporte: " +salud.getDni(),font)));
       table.addCell(new PdfPCell(new Phrase("Edad: "+salud.getEdad(),font)));
      table.addCell(new PdfPCell(new Phrase("Sexo: "+salud.getSexo(),font)));

// Tercera fila
       table.addCell(new PdfPCell(new Phrase("Domicilio: " +salud.getDomicilio(),font))).setColspan(2);
        table.addCell(new PdfPCell(new Phrase("")));
       table.addCell(new PdfPCell(new Phrase("Celular: "+salud.getCelular(),font))).setColspan(2);
        table.addCell(new PdfPCell(new Phrase("")));

// Cuarta fila
      table.addCell(new PdfPCell(new Phrase("Mail: "+salud.getMail(),font))).setColspan(2);
        table.addCell(new PdfPCell(new Phrase("")));
      table.addCell(new PdfPCell(new Phrase("Profesión: "+salud.getProfesion(),font)));
      table.addCell(new PdfPCell(new Phrase("Ocupación: "+ salud.getOcupacion(),font)));

// Quinta fila
        table.addCell(new PdfPCell(new Phrase("Grupo Sanguíneo: "+salud.getGsanguineo(),font)));
       table.addCell(new PdfPCell(new Phrase("Factor: "+salud.getFactor(),font)));
      table.addCell(new PdfPCell(new Phrase("Altura: "+salud.getAltura(),font)));
      table.addCell(new PdfPCell(new Phrase("Peso: "+salud.getPeso(),font)));

// Sexta fila
        table.addCell(new PdfPCell(new Phrase("¿Tiene experiencia previa en cabalgata?: "+salud.getExpcab(),font))).setColspan(2);
        table.addCell(new PdfPCell(new Phrase("")));
        table.addCell(new PdfPCell(new Phrase("¿De que duración?: "+salud.getExpdur(),font))).setColspan(2);
        table.addCell(new PdfPCell(new Phrase("")));

        doc.add(table); // Agregamos la tabla al documento
        Paragraph emergencia = new Paragraph("En caso de emergencia Comunicarse con:", font);
        emergencia.setAlignment(Element.ALIGN_CENTER);
        doc.add(emergencia);
      doc.add(o);
        PdfPTable table2 = new PdfPTable(2); // 4 es el número máximo de columnas que tendrá la tabla
        table2.setWidthPercentage(100);

        //primeraFila
        PdfPCell cell2 = new PdfPCell(new Phrase("Apellido y Nombre: "+salud.getEmerayn(),font));
        cell2.setColspan(2); // Indicamos que esta celda ocupa las 4 columnas de la tabla
        table2.addCell(cell2);

        table2.addCell(new PdfPCell(new Phrase("Domicilio: "+salud.getEmerdom(),font))).setColspan(2);
        table2.addCell(new PdfPCell(new Phrase("")));

        table2.addCell(new PdfPCell(new Phrase("Parentesco: "+salud.getEmerparen(),font)));
        table2.addCell(new PdfPCell(new Phrase("Teléfono: "+salud.getEmertelefono(),font)));

        table2.addCell(new PdfPCell(new Phrase("Datos de Seguro Médico (Nombre y Número): "+salud.getDatosseguromedico(),font))).setColspan(2);
        table2.addCell(new PdfPCell(new Phrase("")));
        doc.add(table2);


        Paragraph clinico = new Paragraph("DATOS CLÍNICOS - OTROS ", font);
        clinico.setAlignment(Element.ALIGN_CENTER);
        doc.add(clinico);
      doc.add(o);




        PdfPTable table3 = new PdfPTable(20); // 4 es el número máximo de columnas que tendrá la tabla
        table3.setWidthPercentage(100);

        //primeraFila
        PdfPCell cell3 = new PdfPCell(new Phrase("APARATO RESPIRATORIO",font));
        cell3.setColspan(8); // Indicamos que esta celda ocupa las 4 columnas de la tabla
        table3.addCell(cell3);

        table3.addCell(new PdfPCell(new Phrase("Si",font)));
        table3.addCell(new PdfPCell(new Phrase("No",font)));
        table3.addCell(new PdfPCell(new Phrase("APARATO CIRCULATORIO",font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Si",font)));
        table3.addCell(new PdfPCell(new Phrase("No",font)));
        table3.addCell(new PdfPCell(new Phrase("¿Sufre de alguna afección respiratoria?: "+salud.getCualafeccionresp(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        if(salud.getAfeccionresp().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
        }else{
          table3.addCell(new PdfPCell(new Phrase("")));
          table3.addCell(new PdfPCell(new Phrase("X")));

        }
        table3.addCell(new PdfPCell(new Phrase("¿Tiene problemas de coagulación?",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getCoag().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        //===2
        table3.addCell(new PdfPCell(new Phrase("Anginas frecuentes",font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getAnginas().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Hemorragias o enfermedades de la sangre",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getHemoragia().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//===3
        table3.addCell(new PdfPCell(new Phrase("Asma bronquial ",font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getAsma().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("¿Sufre de problemas de presión?",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getProbpres().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//====4
        table3.addCell(new PdfPCell(new Phrase("Otras: "+salud.getOtrasrespiratorio(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Otras: "+salud.getOtrascirc(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));

        //====5
        table3.addCell(new PdfPCell(new Phrase("APARATO DIGESTIVO",font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Si")));
        table3.addCell(new PdfPCell(new Phrase("No")));
        table3.addCell(new PdfPCell(new Phrase("APARATO CARDIOVASCULAR",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Si")));
        table3.addCell(new PdfPCell(new Phrase("No")));
//====6
        table3.addCell(new PdfPCell(new Phrase("¿Sufre de problemas gastrointestinales?"+salud.getCualprobgast(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getPrbgastr().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Cardiopatía congénita ",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getCardiopatia().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//===7
        table3.addCell(new PdfPCell(new Phrase("Otras: "+salud.getOtrosdigestivo(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Soplos cardiacos: ",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getSoplo().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//===8
        table3.addCell(new PdfPCell(new Phrase(""))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Tensión arterial habitual: "+salud.getTension(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
//==9
        table3.addCell(new PdfPCell(new Phrase(""))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Pulso cardiaco: "+salud.getPulso(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
//===10
  /*     table3.addCell(new PdfPCell(new Phrase("¿Sufre de alguna afección respiratoria? ¿Cuál?",font))).setColspan(8);
       table3.addCell(new PdfPCell(new Phrase("")));
      table3.addCell(new PdfPCell(new Phrase("")));
       table3.addCell(new PdfPCell(new Phrase("")));
       table3.addCell(new PdfPCell(new Phrase("")));
      table3.addCell(new PdfPCell(new Phrase("")));
      table3.addCell(new PdfPCell(new Phrase("")));
       table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("¿Tiene problemas de coagulación?",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));*/

//===11
        table3.addCell(new PdfPCell(new Phrase("OTROS",font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("OTROS",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
//====12
        table3.addCell(new PdfPCell(new Phrase("¿Es usted alérgico a medicamento?: "+salud.getCualesmedicamentos(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getMedalerg().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Epilepsia",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getEpilepcia().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }

        //====13
        table3.addCell(new PdfPCell(new Phrase("Lesiones de cintura, rodillas o tobillos"+salud.getLescintura(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getCualcintura().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Convulsiones. Que las provoca: "+salud.getQueprovcomb(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getConvulcion().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//====14
        table3.addCell(new PdfPCell(new Phrase("Lesiones de hombros, columna o brazos: "+salud.getCualhombro(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getLeshombro().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Es usted una persona friolenta ",font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getFriolenta().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//===15
        table3.addCell(new PdfPCell(new Phrase("Diabetes",font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getDiabetes().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Esta usted embarazada. ¿Cuántos meses?: "+salud.getMeses(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getEmbarazo().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//===16
        table3.addCell(new PdfPCell(new Phrase("Lesiones en la cabeza, pérdida de conocimiento: ",font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getLescabeza().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Esta usted bajo tratamiento médico: "+salud.getCualtratamiento(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getTratmedico().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
//==17
        table3.addCell(new PdfPCell(new Phrase("Cirugías: "+salud.getCualcigia(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getCirugia().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("Fobias: "+salud.getFobias(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
//===18
        table3.addCell(new PdfPCell(new Phrase("Otro tipo de alergias: "+salud.getOtroalergia(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("Problemas musculares: "+salud.getCualesmusculres(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getCualesmusculres().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        //===19
        table3.addCell(new PdfPCell(new Phrase("¿Sufre actualmente alguna enfermedad?: "+salud.getCualenfermedad(),font))).setColspan(8);
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getSufreeenfermedad().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }
        table3.addCell(new PdfPCell(new Phrase("¿Toma algún medicamento actualmente?: "+salud.getCualmed(),font))).setColspan(8);;
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("")));
      if(salud.getTomamed().equals("Si")){
        table3.addCell(new PdfPCell(new Phrase("X")));
        table3.addCell(new PdfPCell(new Phrase("")));
      }else{
        table3.addCell(new PdfPCell(new Phrase("")));
        table3.addCell(new PdfPCell(new Phrase("X")));

      }

        doc.add(table3);


        Paragraph rest = new Paragraph("RESTRICCIONES ALIMENTARIAS", font);
        rest.setAlignment(Element.ALIGN_CENTER);
        doc.add(rest);


        Paragraph rest1 = new Paragraph("Necesita algún régimen de comida especial. ¿Cuál? "+salud.getRegimen()+" ¿Hay algún alimento que no consuma o alguno que no sea de su agrado? "+salud.getAlimento()+"\n", font);
        rest1.setAlignment(Element.ALIGN_LEFT);
        doc.add(rest1);




        PdfPTable table11 = new PdfPTable(2);
        table11.setWidthPercentage(100);

        PdfPCell cell11 = new PdfPCell(new Phrase("\n________________________ \n Fecha" ,font));
        cell11.setBorder(Rectangle.NO_BORDER); // Establecer sin borde
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table11.addCell(cell11);



        table11.addCell(new PdfPCell(new Phrase("\n__________________________\n Firma del Cliente",font))).setBorder(Rectangle.NO_BORDER);

        doc.add(table11);




      PdfPTable tablex = new PdfPTable(3);
      tablex.setWidthPercentage(100);

      PdfPCell cell111 = new PdfPCell(new Phrase("\n José Hernández s/n - San Francisco - Dpto. Valle Grande - Jujuy / Tel: (0388) 154971402 (03886) 15477699", font));
      cell111.setColspan(3);
      cell111.setBorder(Rectangle.NO_BORDER);
      cell111.setVerticalAlignment(Element.ALIGN_BOTTOM);
      tablex.addCell(cell111);

      Resources res = context.getResources();
      Drawable emailDrawable = res.getDrawable(R.drawable.gmail);
      Drawable facebookDrawable = res.getDrawable(R.drawable.facebook);
      Drawable instagramDrawable = res.getDrawable(R.drawable.instagram);

      ByteArrayOutputStream emailStream = new ByteArrayOutputStream();
      Bitmap emailBitmap = ((BitmapDrawable) emailDrawable).getBitmap();
      emailBitmap.compress(Bitmap.CompressFormat.PNG, 100, emailStream);
      byte[] emailByteArray = emailStream.toByteArray();
      Image emailIcon = Image.getInstance(emailByteArray);
      emailIcon.scaleAbsolute(20f, 20f);

      ByteArrayOutputStream facebookStream = new ByteArrayOutputStream();
      Bitmap facebookBitmap = ((BitmapDrawable) facebookDrawable).getBitmap();
      facebookBitmap.compress(Bitmap.CompressFormat.PNG, 100, facebookStream);
      byte[] facebookByteArray = facebookStream.toByteArray();
      Image facebookIcon = Image.getInstance(facebookByteArray);
      facebookIcon.scaleAbsolute(20f, 20f);

      ByteArrayOutputStream instagramStream = new ByteArrayOutputStream();
      Bitmap instagramBitmap = ((BitmapDrawable) instagramDrawable).getBitmap();
      instagramBitmap.compress(Bitmap.CompressFormat.PNG, 100, instagramStream);
      byte[] instagramByteArray = instagramStream.toByteArray();
      Image instagramIcon = Image.getInstance(instagramByteArray);
      instagramIcon.scaleAbsolute(20f, 20f);

      PdfPCell cell222a = new PdfPCell();
      cell222a.setBorder(Rectangle.NO_BORDER);
      Paragraph emailParagraph = new Paragraph();
      emailParagraph.add(new Chunk(emailIcon, 0, 0));
      emailParagraph.add(new Phrase(" paraisodelasyungas@gmail.com",font));
      emailParagraph.setAlignment(Element.ALIGN_CENTER);
      cell222a.addElement(emailParagraph);
      cell222a.setVerticalAlignment(Element.ALIGN_TOP);
      tablex.addCell(cell222a);

      PdfPCell cell222b = new PdfPCell();
      cell222b.setBorder(Rectangle.NO_BORDER);
      Paragraph facebookParagraph = new Paragraph();
      facebookParagraph.add(new Chunk(facebookIcon, 0, 0));
      facebookParagraph.add(new Phrase(" ParaisodelasYungas",font));
      facebookParagraph.setAlignment(Element.ALIGN_CENTER);
      cell222b.addElement(facebookParagraph);
      cell222b.setVerticalAlignment(Element.ALIGN_TOP);
      tablex.addCell(cell222b);

      PdfPCell cell222c = new PdfPCell();
      cell222c.setBorder(Rectangle.NO_BORDER);
      Paragraph instagramParagraph = new Paragraph();
      instagramParagraph.add(new Chunk(instagramIcon, 0, 0));
      instagramParagraph.add(new Phrase(" paraisodelasyungas",font));
      instagramParagraph.setAlignment(Element.ALIGN_CENTER);
      cell222c.addElement(instagramParagraph);
      cell222c.setVerticalAlignment(Element.ALIGN_MIDDLE);
      tablex.addCell(cell222c);

      doc.add(tablex);


      // Close the document
        doc.close();
    }
}