/**
 * 
 */
package com.opl.mudra.api.mca;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author sanket
 *
 */
public class CustomDateSerializer extends StdSerializer<Date> {
	private static final Logger logger = LoggerFactory.getLogger(CustomDateSerializer.class);
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter
            = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatter1
            = new SimpleDateFormat("yyyy-MM-dd");

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize (Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        Date date = null;
        try {
            //if not valid, it will throw ParseException
            date = formatter.parse(formatter.format(value));
        } catch (ParseException e) {
            try {
                date = formatter1.parse(formatter1.format(value));
            }catch(Exception ex) {
            	logger.info("Error ->"+ ex);
            }
        }
        gen.writeString(formatter.format(date));
    }
}