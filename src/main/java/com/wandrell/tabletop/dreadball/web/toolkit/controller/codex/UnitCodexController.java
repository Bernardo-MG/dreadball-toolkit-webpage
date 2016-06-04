package com.wandrell.tabletop.dreadball.web.toolkit.controller.codex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.tabletop.dreadball.web.toolkit.repository.unit.UnitTemplateRepository;

@Controller
@RequestMapping("/codex/unit")
public class UnitCodexController {

	final UnitTemplateRepository unitRepo;

	@Autowired
	public UnitCodexController(final UnitTemplateRepository unitRepository) {
		super();

		unitRepo = checkNotNull(unitRepository);
	}

	@RequestMapping(method = RequestMethod.GET)
	public final String getAllUnits(final ModelMap model) {
		model.put("players", getUnitRepository().findAll());

		return "unitsList";
	}

	private final UnitTemplateRepository getUnitRepository() {
		return unitRepo;
	}

}
