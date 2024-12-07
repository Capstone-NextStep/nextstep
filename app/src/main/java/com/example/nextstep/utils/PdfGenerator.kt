package com.example.nextstep.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PdfGenerator {
    // Generate PDF from a view
    fun generatePdfFromView(context: Context, view: android.view.View, fileName: String): File? {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(view.width, view.height, 1).create()
//        val pageInfo = PdfDocument.PageInfo.Builder(2480, 3508, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas: Canvas = page.canvas
        view.draw(canvas)

        pdfDocument.finishPage(page)

        return savePdfFile(context, pdfDocument, fileName)
    }

    // Generate PDF from bitmap
    fun generatePdfFromBitmap(context: Context, bitmap: Bitmap, fileName: String): File? {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas: Canvas = page.canvas
        canvas.drawBitmap(bitmap, 0f, 0f, null)

        pdfDocument.finishPage(page)

        return savePdfFile(context, pdfDocument, fileName)
    }

    // Save PDF to file
    private fun savePdfFile(context: Context, pdfDocument: PdfDocument, fileName: String): File? {
        val file = File(context.getExternalFilesDir(null), "${fileName}.pdf")

        try {
            FileOutputStream(file).use { fos ->
                pdfDocument.writeTo(fos)
            }
            pdfDocument.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}