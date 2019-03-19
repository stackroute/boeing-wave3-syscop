import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JavaGraphsComponent } from './java-graphs.component';

describe('JavaGraphsComponent', () => {
  let component: JavaGraphsComponent;
  let fixture: ComponentFixture<JavaGraphsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JavaGraphsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JavaGraphsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
