import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine

new File("./test2.html").withWriter("UTF-8"){writer->
    new SimpleTemplateEngine()
        .createTemplate( new File("./test.gsp") )
        .make( data:new JsonSlurper().parse(new File("./Data.json")) )
        .writeTo( writer )
}
