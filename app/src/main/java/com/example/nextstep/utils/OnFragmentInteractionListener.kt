package com.example.nextstep.utils

/*
* OnFragmentInteractionListener interface digunakan untuk
* mengubah selected bottom nav menu di Main Activity sesuai dengan fragment yang di pilih
* di Home Fragment, agar Home Fragment mempunyai akses ke Bottom nav
* contohnya: ketika menu Iv roadmap di pilih, maka menu item bottom nav juga berubah
* */
interface OnFragmentInteractionListener {
    fun onRoadmapSelected()
    fun onSearchSelected()
}