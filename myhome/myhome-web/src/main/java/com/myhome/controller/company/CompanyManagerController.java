package com.myhome.controller.company;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.myhome.domain.Company;
import com.myhome.form.CompanyForm;
import com.myhome.service.ICompanyService;

@Controller
@SessionAttributes(value = "asimplecompagny")
// met en session l attribut asimplecompagny apres l'appel de requestmapping
public class CompanyManagerController {

	@Autowired
	private ICompanyService companyService;

	// ---------- Spring MVC appelle toutes les méthodes @ModelAttribute avant
	// d’appeler la méthode @RequestMapping -
	@ModelAttribute("asimplecompagny")
	public Company populatePersonne(@RequestParam(required = false) String name) {
		return new Company(name, 50, "Genova");
	}

	@RequestMapping(value = "/manageCompany", method = RequestMethod.GET)
	public String display(ModelMap pModel, HttpSession session) {

		System.out.println(pModel);

		System.out.println(String.format("session = %s ===>",
				session.getAttribute("asimplecompagny")));
		final List<Company> companyList = companyService.findAllCompanies();
		pModel.addAttribute("companyList", companyList);
		return "company/list";
	}

	@RequestMapping(value = "/redirectToCreateCompany", method = RequestMethod.GET)
	public String redirectToCreate(
			@ModelAttribute("companyForm") CompanyForm companyForm,
			ModelMap pModel) {
		return "company/edit";
	}

	@RequestMapping(value = "/redirectToUpdateCompany", method = RequestMethod.GET)
	public String redirectToUpdate(ModelMap pModel,
			@ModelAttribute("companyForm") CompanyForm form,
			@RequestParam(value = "idCompany") final Integer idCompany) {

		Company company = companyService.findCompanyById(idCompany);
		if (company != null) {
			form.setId(company.getId());
			form.setName(company.getName());
			form.setHeadoffice(company.getHeadoffice());
			form.setEmployees(String.valueOf(company.getEmployees()));
		}
		return "company/edit";
	}

	@RequestMapping(value = "/saveCompany", params = "save", method = RequestMethod.POST)
	public String save(
			@Valid @ModelAttribute(value = "companyForm") final CompanyForm formBean,
			final BindingResult pBindingResult, final ModelMap pModel) {

		if (!pBindingResult.hasErrors()) {
			Company company = new Company();
			Long id = formBean.getId();
			if (id != null) {
				company.setId(id);
			}

			company.setName(formBean.getName());
			company.setHeadoffice(formBean.getHeadoffice());
			company.setEmployees(Long.valueOf(formBean.getEmployees()));
			companyService.saveCompany(company);
			return "redirect:manageCompany";
		}

		return "company/edit";
	}

	@RequestMapping(value = "/saveCompany", params = "cancel", method = RequestMethod.POST)
	public String cancelSave(final ModelMap pModel) {
		return "redirect:manageCompany";
	}

	@RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
	public String delete(
			@RequestParam(value = "idCompany") final Integer idCompany,
			final ModelMap pModel) {
		final Company company = new Company();
		company.setId(idCompany);
		companyService.deleteCompany(company);
		return "redirect:manageCompany";
	}

}
