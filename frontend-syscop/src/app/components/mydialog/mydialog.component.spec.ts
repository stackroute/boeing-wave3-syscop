import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MydialogComponent } from './mydialog.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatInputModule } from '@angular/material';
import { MatSelectModule} from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';



describe('MydialogComponent', () => {
  let component: MydialogComponent;
  let fixture: ComponentFixture<MydialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MydialogComponent],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      imports: [HttpClientTestingModule,
        RouterTestingModule,
        FormsModule,
        ReactiveFormsModule,
        MatSelectModule,
        MatInputModule,
        BrowserAnimationsModule ],
      providers: [{ provide: MatDialogRef, }, { provide: MAT_DIALOG_DATA, }]

    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MydialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
