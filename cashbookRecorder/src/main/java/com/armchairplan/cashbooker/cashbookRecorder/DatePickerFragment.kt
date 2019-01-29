package com.armchairplan.cashbooker.cashbookRecorder

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_YEAR = "paramYear"
private const val ARG_MONTH = "paramMonth"
private const val ARG_DAY= "paraDAY"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DatePickerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DatePickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DatePickerFragment : DialogFragment() { //, DatePickerDialog.OnDateSetListener

    // TODO: Rename and change types of parameters
    //private var listener: OnFragmentInteractionListener? = null

    //open var mOnDateSelected: DatePickerDialog.OnDateSetListener? = null
    var mOnDateSelected: OnSelectedDateListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()

        val year = arguments?.getInt(ARG_YEAR) ?: c.get(Calendar.YEAR)
        val month = arguments?.getInt(ARG_MONTH) ?: c.get(Calendar.MONTH)
        val day = arguments?.getInt(ARG_DAY) ?: c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
                activity,
                mOnDateSetListener,
                year,
                month,
                day
        )
    }

    private val mOnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        val c = Calendar.getInstance()
        c.set(year, month, day)
        mOnDateSelected?.onSelectedDate(view, c.time)
    }

/*
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        mOnSelected?.onSelectedDate(p0, year, month, day)
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        //val date = String.format(Locale.US, "%d/%d/%d", year, month+1, day)
        //Log.d("DatePickerFragment", "onDateSet: date: $date")
    }
*/
/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }
*/
/*
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
*/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSelectedDateListener) {
            mOnDateSelected = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        //listener = null
        mOnDateSelected = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    /*
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
    */
    interface OnSelectedDateListener {
        fun onSelectedDate(view: DatePicker?, date: Date)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param year
         * @param montb
         * @param day
         * @return A new instance of fragment DatePickerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(strDate: String) =
                DatePickerFragment().apply {
                    if (strDate.isNotBlank()) {
                        val date = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/M/d"))
                        arguments = Bundle().apply {
                            putInt(ARG_YEAR, date.year)
                            putInt(ARG_MONTH, date.monthValue)
                            putInt(ARG_DAY, date.dayOfMonth)
                        }
                    }
                }
        fun newInstance() = DatePickerFragment()
    }
}
