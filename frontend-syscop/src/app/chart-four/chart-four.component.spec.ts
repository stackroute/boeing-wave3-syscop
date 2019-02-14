import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartFourComponent } from './chart-four.component';

describe('ChartFourComponent', () => {
  let component: ChartFourComponent;
  let fixture: ComponentFixture<ChartFourComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChartFourComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartFourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
