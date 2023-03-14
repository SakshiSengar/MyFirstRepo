import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine

new File("C:/Users/SakshiSengar/Desktop/Work/Documentation/PerformanceTesting/Groovy/TestExample/test2.html").withWriter("UTF-8"){writer->
    new SimpleTemplateEngine()
        .createTemplate( new File("C:/Users/SakshiSengar/Desktop/Work/Documentation/PerformanceTesting/Groovy/TestExample/test.gsp") )
        .make( data:new JsonSlurper().parse(new File("C:/Users/SakshiSengar/Desktop/Work/Documentation/PerformanceTesting/Groovy/TestExample/Data.json")) )
        .writeTo( writer )
}
