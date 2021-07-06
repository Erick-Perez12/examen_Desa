package com.makotojava.learn.springboot.web;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.makotojava.learn.odot.exception.ServiceException;
import com.makotojava.learn.odot.model.Category;
import com.makotojava.learn.springboot.SpringBootDemoController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/CategoryRestService")
public class CategoryRestService extends SpringBootDemoController {

  @RequestMapping("/FindAll")
  public List<Category> findAll() {
    return getCategoryService().findAll();
  }

  @RequestMapping("/FindById/{id}")
  public Category findById(@PathVariable Long id) {
    return getCategoryService().findById(id);
  }

  @RequestMapping("/FindByName/{name}")
  public Category findByName(@PathVariable String name) {
    return getCategoryService().findByName(name);
  }
  @RequestMapping("/FindByDescription/{description}")
  public Category findByDescription(@PathVariable String description) {
    return getCategoryService().findByDescription(description);
  }

  @RequestMapping(value = "/Add", method = RequestMethod.POST)
  public Category add(@RequestBody Category category) {
    Category ret;
    try {
      ret = getCategoryService().add(category);
    } catch (ServiceException e) {
      throw new RuntimeException("Could not add Category: " + ReflectionToStringBuilder.toString(category), e);
    }
    return ret;
  }

  @RequestMapping(value = "/Update", method = RequestMethod.POST)
  public String update(@RequestBody Category category) {
    String ret = "UPDATE FAILED";
    try {
      boolean updated = getCategoryService().update(category);
      if (updated) {
        ret = "UPDATE SUCCESSFUL";
      }
    } catch (ServiceException e) {
      throw new RuntimeException("Could not update Category: " + ReflectionToStringBuilder.toString(category), e);
    }
    return ret;
  }

  @RequestMapping(value = "/Delete", method = RequestMethod.DELETE)
  public String delete(@RequestBody Category category) {
    String ret = "DELETE FAILED";
    try {
      getCategoryService().delete(category);
      ret = "DELETE SUCCESSFUL";
    } catch (ServiceException e) {
      throw new RuntimeException("Could not delete Category: " + ReflectionToStringBuilder.toString(category), e);
    }
    return ret;
  }

}
