import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

import { ReportService } from '../report.service';
import { Utils } from '../../utils';
import { Report } from '../report';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'app-profit-loss-statement',
  templateUrl: './profit-loss-statement.component.html',
  styleUrls: ['./profit-loss-statement.component.sass']
})
export class ProfitLossStatementComponent implements OnInit {

  rForm: FormGroup;

  maxDate = new Date();
  minDate: Date;
  currentEndDate = new Date();
  currentStartDate: Date;

  data: any;

  report: Report;

  chartType = 'line';

  constructor(private fb: FormBuilder,
              private reportService: ReportService) {
  }

  loadReport(): void {
    this.reportService
      .getReport(Utils.dateToString(this.currentStartDate), Utils.dateToString(this.currentEndDate))
      .subscribe(report => {
        this.report = report;
        this.loadData();
      });
  }

  loadData(): void {
    if (this.chartType === 'line') {
      this.loadLineData();
    } else {
      this.loadPieData();
    }
  }

  loadLineData(): void {
    this.data = {
      labels: this.report.completeDates,
      datasets: [
        {
          label: 'Income',
          data: this.report.income,
          fill: false,
          borderColor: '#9CCC65'
        },
        {
          label: 'Expenses',
          data: this.report.expenses,
          fill: false,
          borderColor: '#FF7043'
        },
        {
          label: 'Profit',
          data: this.report.profit,
          fill: true,
          borderColor: '#26A69A',
          backgroundColor: '#B2DFDB'
        }
      ]
    };
  }

  loadPieData(): void {
    this.data = {
      labels: this.report.completeDates,
      datasets: [
        {
          label: 'Income',
          backgroundColor: '#9CCC65',
          borderColor: '#7CB342',
          data: this.report.income
        },
        {
          label: 'Expenses',
          backgroundColor: '#FF7043',
          borderColor: '#F4511E',
          data: this.report.expenses
        },
        {
          label: 'Profit',
          backgroundColor: '#26A69A',
          borderColor: '#00897B',
          data: this.report.profit
        }
      ]
    };
  }

  downloadReport(): void {
    if (this.currentStartDate && this.currentEndDate) {
      const startDate = Utils.dateToString(this.currentStartDate);
      const endDate = Utils.dateToString(this.currentEndDate);

      this.reportService
        .downloadExcelReport(startDate, endDate)
        .subscribe(data => {
          FileSaver.saveAs(data, `report-${startDate}-${endDate}.xlsx`);
        });
    }
  }

  get startDate(): FormControl {
    return this.rForm.controls.startDate as FormControl;
  }

  get endDate(): FormControl {
    return this.rForm.controls.endDate as FormControl;
  }

  initFormListener(): void {
    this.rForm.valueChanges.subscribe(value => {
      if (this.chartType !== value.chartType) {
        this.chartType = value.chartType;
        this.loadData();
      }

      if (!value.startDate || !value.endDate) {
        return;
      }

      if (value.startDate.getTime() > value.endDate.getTime()) {
        this.startDate.setValue(value.endDate);
        return;
      }

      if (this.currentEndDate.getTime() !== value.endDate.getTime()
        || this.currentStartDate.getTime() !== value.startDate.getTime()) {

        this.currentEndDate = value.endDate;
        this.currentStartDate = value.startDate;

        this.loadReport();
      }
    });
  }

  initForm() {
    this.currentStartDate = new Date(this.currentEndDate);
    this.currentStartDate.setMonth(this.currentEndDate.getMonth() - 1);

    this.rForm = this.fb.group({
      startDate: this.currentStartDate,
      endDate: this.currentEndDate,
      chartType: true
    });
  }

  ngOnInit() {
    this.initForm();
    this.initFormListener();
    this.loadReport();
  }

}
