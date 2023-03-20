import groovy.json.JsonSlurper

     println("Before creating a jsonfile")
    def inputFile = new File("\regressiontestresults.json")
    println("After creating a jsonfile")
   println System.getenv('PATH') 
 // writeFile inputFile: 'regressiontestresults.json', text: 'easy.'
def InputJSON = new JsonSlurper().parseText(inputFile.text)
def writer = new StringWriter() // html is written here by markup builder
def markup = new groovy.xml.MarkupBuilder(writer) // the builder
String id1 = InputJSON.get("Results")
def id2 = InputJSON.get("Results")
markup.html {
  head {
   style(type: "text/css", '''
    .failedtest {
     color: red;
     text - align: center;
    }
    ''')
   }
   markup.body {
    p "Hi All,"
    p "Please analyze the failures and add your analysis in the remarks column."
    p "Note: This is the first version of the automatic email, we shall keep enhancing the format for automatic color coding and grouping of data."
   } {
    markup.table(border: "1") {
     markup.thead(bgcolor: "blue") {
      markup.tr {
       markup.th("Team")
       markup.th("Profiles")
       markup.th("FrameWork")
       markup.th("Total Tests")
       markup.th("Passed")
       markup.th("Failed")
       markup.th("Skipped")
       markup.th("Results URL")
       markup.th("Date")
       markup.th("Remarks")
      } // tr
     } // thead
     def dateinhtml
     def teamName
     markup.tbody {
      markup.tr {
       for (def data: id2) {
        markup.tr {
         teamName = data.pyTextValue.toString()
         markup.td(align: "left", teamName.substring(1, teamName.length() - 1))
         markup.td(align: "left", data.Profile)
         markup.td(align: "left", data.ToolRunner)
         markup.td(align: "center", data.TotalTests)
         markup.td(align: "center", data.Passed)
         if (data.Failed.toInteger() == 0) {
          markup.td(align: "center", data.Failed)
         } else {
          markup.td('class': 'failedtest', data.Failed)
         }
         markup.td(align: "center", data.Skipped)
         markup.td(align: "left", data.ResultsURL)
         //  markup.td(align:"right",data.DateTime)
         dateinhtml = data.DateTimeValue.toString()
         markup.td(align: "right", dateinhtml.substring(1, 11))
        } // foreach
       } // td
      } // tr
     } //tbody
    } // table
   }
      println("Before creating a html")
   def newFile = new File("/regressiontestresults.html")
      println("After creating a html")
   // writeFile newFile: 'regressiontestresults.html', text:  newFile.write(writer.toString())
     newFile.write(writer.toString())
      println("After writing html to string")
}
return this
