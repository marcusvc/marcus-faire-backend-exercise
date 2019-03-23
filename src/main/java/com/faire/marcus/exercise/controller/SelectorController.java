package com.faire.marcus.exercise.controller;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.selector.AddressSelector;
import com.faire.marcus.exercise.selector.BackorderedSelector;
import com.faire.marcus.exercise.selector.NegativeQuantitiesSelector;
import com.faire.marcus.exercise.selector.ProcessableSelector;
import com.faire.marcus.exercise.selector.TeaDropBestSellingSelector;
import com.faire.marcus.exercise.selector.TeaDropMoreExpensiveOrderSelector;
import com.faire.marcus.exercise.selector.TeaDropSelector;

@Component
public class SelectorController {
	
	private TeaDropSelector teaDropSelector;
	private BackorderedSelector backorderedSelector;
	private ProcessableSelector processableSelector;
	private NegativeQuantitiesSelector negativeQuantitiesSelector;
	private TeaDropBestSellingSelector teaDropBestSellingSelector;
	private TeaDropMoreExpensiveOrderSelector teaDropMoreExpensiveOrderSelector;
	private AddressSelector addressSelector;
	
	public SelectorController(TeaDropSelector teaDropSelector, BackorderedSelector backorderedSelector,
			ProcessableSelector processableSelector, NegativeQuantitiesSelector negativeQuantitiesSelector,
			TeaDropBestSellingSelector teaDropBestSellingSelector, AddressSelector addressSelector,
			TeaDropMoreExpensiveOrderSelector teaDropMoreExpensiveOrderSelector) {
		this.teaDropSelector = teaDropSelector;
		this.backorderedSelector = backorderedSelector;
		this.processableSelector = processableSelector;
		this.negativeQuantitiesSelector = negativeQuantitiesSelector;
		this.teaDropBestSellingSelector = teaDropBestSellingSelector;
		this.addressSelector = addressSelector;
		this.teaDropMoreExpensiveOrderSelector = teaDropMoreExpensiveOrderSelector;
	}

	public TeaDropSelector getTeaDropSelector() {
		return teaDropSelector;
	}

	public BackorderedSelector getBackorderedSelector() {
		return backorderedSelector;
	}

	public ProcessableSelector getProcessableSelector() {
		return processableSelector;
	}

	public NegativeQuantitiesSelector getNegativeQuantitiesSelector() {
		return negativeQuantitiesSelector;
	}

	public TeaDropBestSellingSelector getTeaDropBestSellingSelector() {
		return teaDropBestSellingSelector;
	}

	public TeaDropMoreExpensiveOrderSelector getTeaDropMoreExpensiveOrderSelector() {
		return teaDropMoreExpensiveOrderSelector;
	}

	public AddressSelector getAddressSelector() {
		return addressSelector;
	}

}
