import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InitialViewComponent } from './initial-view.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

describe('InitialViewComponent', () => {
  let component: InitialViewComponent;
  let fixture: ComponentFixture<InitialViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InitialViewComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InitialViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
