package lanaDrahrepus.controllers;

import lanaDrahrepus.model.Customer;
import lanaDrahrepus.service.CustomerService;
import lanaDrahrepus.service.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();

    public CustomerController() {
    }

    @GetMapping({"/"})
    public String index(Model model) {
        model.addAttribute("customers", this.customerService.findAll());
        return "index";
    }

    @GetMapping({"/customer/create"})
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping({"/customer/save"})
    public String save(Customer customer, RedirectAttributes redirect) {
        customer.setId((int)(Math.random() * 10000.0D));
        this.customerService.save(customer);
        redirect.addFlashAttribute("success", "Saved customer successfully!");
        return "redirect:/";
    }

    @GetMapping({"/customer/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", this.customerService.findById(id));
        return "edit";
    }

    @PostMapping({"/customer/update"})
    public String update(Customer customer, RedirectAttributes redirect) {
        this.customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/";
    }

    @GetMapping({"/customer/{id}/delete"})
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", this.customerService.findById(id));
        return "delete";
    }

    @PostMapping({"/customer/delete"})
    public String delete(Customer customer, RedirectAttributes redirect) {
        this.customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/";
    }

    @GetMapping({"/customer/{id}/view"})
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", this.customerService.findById(id));
        return "view";
    }
}
