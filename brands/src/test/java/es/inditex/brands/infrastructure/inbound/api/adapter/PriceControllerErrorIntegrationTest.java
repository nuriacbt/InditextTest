package es.inditex.brands.infrastructure.inbound.api.adapter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import es.inditex.brands.domain.ports.primary.IPriceService;
import es.inditex.brands.infrastructure.inbound.api.exception.CustomGlobalExceptionHandler;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectBrandIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.IncorrectProductIdException;
import es.inditex.brands.infrastructure.inbound.api.exception.PriceNotFoundException;

/**
 * @author Núria Curto
 *
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class PriceControllerErrorIntegrationTest {
	
	@Mock
	private IPriceService service;
	
    @InjectMocks
    private PriceController controller;

    private MockMvc mvc;

    private ExceptionHandlerExceptionResolver createExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(
                HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(
                		CustomGlobalExceptionHandler.class).resolveMethod(exception);
                return new ServletInvocableHandlerMethod(
                    new CustomGlobalExceptionHandler(), method);
            }
        };
        exceptionResolver.getMessageConverters().add( new MappingJackson2HttpMessageConverter());
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }

    public void setup() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setHandlerExceptionResolvers(createExceptionResolver())
                .build();
    }

    @Test
    public void getPriceIncorrectBrandId() throws Exception {
    	this.setup();
    	LocalDateTime dateApl = LocalDateTime.parse("2020-06-16T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    	when(service.getPrice(5, 35455, dateApl)).thenThrow(new IncorrectBrandIdException("ex test"));
    	String url = this.createURLWithParams(5, 35455, "2020-06-16T21:00:00.000-00:00");
        mvc.perform(get(url)).andExpect(status().is5xxServerError());
    }
    
    @Test
    public void getPriceIncorrectProductId() throws Exception {
    	this.setup();
    	LocalDateTime dateApl = LocalDateTime.parse("2020-06-16T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    	when(service.getPrice(1, 88524, dateApl)).thenThrow(new IncorrectProductIdException("ex test"));
    	String url = this.createURLWithParams(1, 88524, "2020-06-16T21:00:00.000-00:00");
        mvc.perform(get(url)).andExpect(status().is5xxServerError());
    }
    
    @Test
    public void getPriceExPriceNotFound() throws Exception {
    	this.setup();
    	LocalDateTime dateApl = LocalDateTime.parse("2020-06-16T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    	when(service.getPrice(1, 35455, dateApl)).thenThrow(new PriceNotFoundException("ex test"));
    	String url = this.createURLWithParams(1, 35455, "2020-06-16T21:00:00.000-00:00");
        mvc.perform(get(url)).andExpect(status().is5xxServerError());
    }
    
    private String createURLWithParams(Integer brandId, Integer productId, String dateStr) {	
		StringBuilder builder = new StringBuilder("/api/v1/brands/").append(brandId).append("/products/")
				.append(productId).append("/prices?aplicationDate=").append(dateStr);
        return builder.toString();
    }
}
